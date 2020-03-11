package nl.novi;

import nl.novi.onderwijsinstellingen.definition.domain.onderwijsinstellingen.Instellingen;
import nl.novi.onderwijsinstellingen.endpoint.DetailEndpoint;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DetailEndpointTest {
    @Mock
    private DataService dataService;

    @InjectMocks
    private DetailEndpoint detailEndpoint = new DetailEndpoint();

    @Test
    public void testDossierNummerSuccessEndpoint() throws IOException {
        String dataXml = this.getFromResources("instellingen.xml");

        Instellingen instellingenType = JAXB.unmarshal(new StringReader(dataXml), Instellingen.class);

        List<Instellingen.Instelling> instellingen = instellingenType.getInstelling();

        String brin = "21PB";
        Stream<Instellingen.Instelling> instellingStream = instellingen.stream().filter(p -> (p.getBRI().equals(brin)));
        List<Instellingen.Instelling> result = instellingStream.collect(Collectors.toList());
        Instellingen.Instelling instelling = result.get(0);

        when(this.dataService.retrieveBy(anyString())).thenReturn(Optional.of(instelling));

        Response response = this.detailEndpoint.retrieve(brin);

        verify(this.dataService, times(1)).retrieveBy(brin);
        verifyNoMoreInteractions(this.dataService);

        Instellingen.Instelling responseEntity = (Instellingen.Instelling) response.getEntity();

        assertEquals(200, response.getStatus());
        assertEquals("wo", responseEntity.getSOORT());
        assertEquals("Zuid-Holland", responseEntity.getPROVINCIE());
        assertEquals("Universiteit Leiden", responseEntity.getINSTELLINGSNAAM());
    }

    @Test
    public void testDossierNummerNotFoundEndpoint() throws IOException {
        String brin = "21PBX";
        when(this.dataService.retrieveBy(anyString())).thenReturn(Optional.empty());

        Response response = this.detailEndpoint.retrieve(brin);

        verify(this.dataService, times(1)).retrieveBy(brin);
        verifyNoMoreInteractions(this.dataService);

        assertEquals(404, response.getStatus());
    }

    private String getFromResources(String filename) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream(filename);

        return IOUtils.toString(inputStream, StandardCharsets.UTF_8);

    }
}
