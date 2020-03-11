package nl.novi;

import nl.novi.onderwijsinstellingen.definition.domain.SearchForRequestObject;
import nl.novi.onderwijsinstellingen.definition.domain.SearchRequest;
import nl.novi.onderwijsinstellingen.definition.domain.ZoekenOpType;
import nl.novi.onderwijsinstellingen.definition.domain.onderwijsinstellingen.Instellingen;
import nl.novi.onderwijsinstellingen.endpoint.SearchEndpoint;
import nl.novi.onderwijsinstellingen.service.DataService;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import javax.xml.bind.JAXB;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchEndpointTest {
    @Mock
    private DataService dataService;

    @InjectMocks
    private SearchEndpoint searchEndpoint = new SearchEndpoint();

    @Test
    public void testSearchEndpoint() throws IOException {
        String dataXml = this.getFromResources("instellingen.xml");

        Instellingen instellingenType = JAXB.unmarshal(new StringReader(dataXml), Instellingen.class);

        List<Instellingen.Instelling> instellingen = instellingenType.getInstelling();

        when(this.dataService.all()).thenReturn(instellingen);

        SearchRequest searchRequest = new SearchRequest();
        List<SearchForRequestObject> searchFor = new ArrayList<>();

        SearchForRequestObject searchForRequestObject = new SearchForRequestObject();
        searchForRequestObject.setZoekOpdrachtType(ZoekenOpType.INSTELLINGSNAAM);
        searchForRequestObject.setZoekWaarde("Universiteit Leiden");

        searchFor.add(searchForRequestObject);
        searchRequest.setSearch(searchFor);

        Response response = this.searchEndpoint.search(searchRequest);

        verify(this.dataService, times(1)).all();
        verify(this.dataService, times(1)).search(any(), any(), any());
        verifyNoMoreInteractions(this.dataService);
    }

    @Test
    public void testSearchMultipleEndpoint() throws IOException {
        String dataXml = this.getFromResources("instellingen.xml");

        Instellingen instellingenType = JAXB.unmarshal(new StringReader(dataXml), Instellingen.class);

        List<Instellingen.Instelling> instellingen = instellingenType.getInstelling();

        when(this.dataService.all()).thenReturn(instellingen);

        SearchRequest searchRequest = new SearchRequest();
        List<SearchForRequestObject> searchFor = new ArrayList<>();

        SearchForRequestObject searchForRequestObject = new SearchForRequestObject();
        searchForRequestObject.setZoekOpdrachtType(ZoekenOpType.INSTELLINGSNAAM);
        searchForRequestObject.setZoekWaarde("Universiteit Leiden");

        searchFor.add(searchForRequestObject);

        SearchForRequestObject searchForRequestObject2 = new SearchForRequestObject();
        searchForRequestObject2.setZoekOpdrachtType(ZoekenOpType.GEMEENTENAAM);
        searchForRequestObject2.setZoekWaarde("LEIDEN");

        searchFor.add(searchForRequestObject2);

        searchRequest.setSearch(searchFor);

        Response response = this.searchEndpoint.search(searchRequest);

        verify(this.dataService, times(1)).all();
        verify(this.dataService, times(2)).search(any(), any(), any());
        verifyNoMoreInteractions(this.dataService);
    }


    private String getFromResources(String filename) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream(filename);

        return IOUtils.toString(inputStream, StandardCharsets.UTF_8);

    }
}
