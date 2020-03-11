package nl.novi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import nl.novi.onderwijsinstellingen.definition.domain.SearchForRequestObject;
import nl.novi.onderwijsinstellingen.definition.domain.SearchRequest;
import nl.novi.onderwijsinstellingen.definition.domain.ZoekenOpType;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.webapp31.WebAppDescriptor;
import org.jboss.shrinkwrap.descriptor.api.webcommon31.WebAppVersionType;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class OnderwijsInstellingenServiceIT {
    @ArquillianResource
    static URL deploymentUrl;

    @Deployment(testable = false)
    public static WebArchive createDeployment() throws IOException {
        File[] files = Maven.resolver()
                .loadPomFromFile("../onderwijs-instellingen-service-war/pom.xml")
                .importCompileAndRuntimeDependencies()
                .resolve()
                .withTransitivity()
                .asFile();

        WebArchive war = ShrinkWrap.create(WebArchive.class, "onderwijs-instellingen-service-war.war")
                .addPackages(true, "nl.novi")
                .addAsWebInfResource(new File("../onderwijs-instellingen-service-war/src/main/webapp/WEB-INF/beans.xml"))
                .setWebXML(new StringAsset(
                        Descriptors.create(WebAppDescriptor.class).version(WebAppVersionType._3_1).exportAsString()));

        war.addAsLibraries(files);

        // Show the deploy structure
        System.out.println(war.toString(true));

        return war;
    }

    @Test
    public void detailOKIntegrationTest() throws UnsupportedEncodingException {
        String brin = "30GB";
        Map<String, Object> resultMapping = this.retrieveDetail(brin);

        assertTrue(resultMapping.containsKey("SOORT"));
        assertTrue(resultMapping.containsKey("PROVINCIE"));
        assertEquals(brin, resultMapping.get("BRI"));
    }


    @Test
    public void searchSingleIntegrationTest() throws UnsupportedEncodingException {
        SearchRequest searchRequest = new SearchRequest();
        List<SearchForRequestObject> searchForList = new ArrayList<>();

        SearchForRequestObject searchFor = new SearchForRequestObject();
        searchFor.setZoekOpdrachtType(ZoekenOpType.INSTELLINGSNAAM);
        searchFor.setZoekWaarde("Fontys Hogescholen");

        searchForList.add(searchFor);

        searchRequest.setSearch(searchForList);

        this.searchFor(searchRequest, 1);
    }

    @Test
    public void searchMultipleIntegrationTest() throws UnsupportedEncodingException {
        SearchRequest searchRequest = new SearchRequest();
        List<SearchForRequestObject> searchForList = new ArrayList<>();

        SearchForRequestObject searchFor = new SearchForRequestObject();
        searchFor.setZoekOpdrachtType(ZoekenOpType.INSTELLINGSNAAM);
        searchFor.setZoekWaarde("Fontys Hogescholen");

        SearchForRequestObject searchFor2nd = new SearchForRequestObject();
        searchFor2nd.setZoekOpdrachtType(ZoekenOpType.GEMEENTENAAM);
        searchFor2nd.setZoekWaarde("EINDHOVEN");

        searchForList.add(searchFor);
        searchForList.add(searchFor2nd);

        searchRequest.setSearch(searchForList);

        this.searchFor(searchRequest, 1);
    }

    @Test
    public void searchNoResultIntegrationTest() throws UnsupportedEncodingException {
        SearchRequest searchRequest = new SearchRequest();
        List<SearchForRequestObject> searchForList = new ArrayList<>();

        SearchForRequestObject searchFor = new SearchForRequestObject();
        searchFor.setZoekOpdrachtType(ZoekenOpType.INSTELLINGSNAAM);
        searchFor.setZoekWaarde("STICHTING ROBIN");

        SearchForRequestObject searchFor2nd = new SearchForRequestObject();
        searchFor2nd.setZoekOpdrachtType(ZoekenOpType.GEMEENTENAAM);
        searchFor2nd.setZoekWaarde("EPE");

        searchForList.add(searchFor);
        searchForList.add(searchFor2nd);

        searchRequest.setSearch(searchForList);
        List<Map<String, Object>> instellingen = this.searchFor(searchRequest, 0);

        assertTrue(instellingen.isEmpty());
    }

    @Test
    public void fullIntegrationTest() throws UnsupportedEncodingException {
        SearchRequest searchRequest = new SearchRequest();
        List<SearchForRequestObject> searchForList = new ArrayList<>();

        SearchForRequestObject searchFor = new SearchForRequestObject();
        searchFor.setZoekOpdrachtType(ZoekenOpType.INSTELLINGSNAAM);
        searchFor.setZoekWaarde("Fontys Hogescholen");

        SearchForRequestObject searchFor2nd = new SearchForRequestObject();
        searchFor2nd.setZoekOpdrachtType(ZoekenOpType.GEMEENTENAAM);
        searchFor2nd.setZoekWaarde("EINDHOVEN");

        searchForList.add(searchFor);
        searchForList.add(searchFor2nd);

        searchRequest.setSearch(searchForList);

        this.integrationTest(searchRequest, 1);
    }

    private List<Map<String, Object>> integrationTest(SearchRequest searchRequest, int expectedResults) throws UnsupportedEncodingException {
        List<Map<String, Object>> instellingen = this.searchFor(searchRequest, expectedResults);

        for (Map<String, Object> instelling : instellingen) {
            String bri = (String) instelling.get("bri");

            Map<String, Object> detailByResult = this.retrieveDetail("30GB");

            assertTrue(detailByResult.get("BRI").equals(bri));
        }

        return instellingen;
    }

    private List<Map<String, Object>> searchFor(SearchRequest searchRequest, int expectedResults) throws UnsupportedEncodingException {
        String uri = deploymentUrl.toString() + "api/search";

        System.out.println(String.format("Resolved URI: [%s]", uri));

        HttpPost request = new HttpPost(uri);

        request.addHeader("Content-Type", "application/json");

        Gson gson = new Gson();
        StringEntity entity = new StringEntity(gson.toJson(searchRequest));
        request.setEntity(entity);

        try (CloseableHttpResponse result = this.getHttpClient().execute(request)) {
            assertEquals(200, result.getStatusLine().getStatusCode());

            String jsonResult = EntityUtils.toString(result.getEntity());

            System.out.println(jsonResult);

            ObjectMapper mapper = new ObjectMapper();

            List<Map<String, Object>> instellingen = mapper.readValue(jsonResult, new TypeReference<List<Map<String, Object>>>() {
            });

            assertEquals(expectedResults, instellingen.size());

            return instellingen;
        } catch (Exception e) {
            fail(e.getMessage());

            e.printStackTrace();

            return Collections.emptyList();
        }
    }

    private Map<String, Object> retrieveDetail(String brin) {
        String uri = deploymentUrl.toString() + "api/detail/by/" + brin;

        System.out.println(String.format("Resolved URI: [%s]", uri));

        HttpGet request = new HttpGet(uri);

        try (CloseableHttpResponse result = this.getHttpClient().execute(request)) {
            assertEquals(200, result.getStatusLine().getStatusCode());

            String jsonResult = EntityUtils.toString(result.getEntity());

            System.out.println(jsonResult);

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> resultMapping = mapper.readValue(jsonResult, new TypeReference<Map<String, Object>>() {
            });

            assertTrue(resultMapping.containsKey("SOORT"));
            assertTrue(resultMapping.containsKey("PROVINCIE"));
            assertTrue(resultMapping.containsKey("INSTELLINGSNAAM"));
            assertTrue(resultMapping.containsKey("BRI"));
            assertTrue(resultMapping.containsKey("PLAATSNAAM"));

            return resultMapping;
        } catch (Exception e) {
            fail(e.getMessage());

            e.printStackTrace();

            return Collections.emptyMap();
        }
    }

    public CloseableHttpClient getHttpClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (x509CertChain, authType) -> true)
                .build();
        httpClientBuilder.setSSLContext(sslContext);

        return httpClientBuilder.build();
    }
}
