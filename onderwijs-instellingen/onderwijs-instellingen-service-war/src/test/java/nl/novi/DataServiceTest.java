package nl.novi;

import nl.novi.onderwijsinstellingen.definition.domain.ZoekenOpType;
import nl.novi.onderwijsinstellingen.definition.domain.onderwijsinstellingen.Instellingen;
import nl.novi.onderwijsinstellingen.service.DataService;
import nl.novi.onderwijsinstellingen.service.XmlDownloadService;
import nl.novi.onderwijsinstellingen.service.impl.DataServiceImpl;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DataServiceTest {
    @Mock
    private XmlDownloadService xmlDownloadService;

    @InjectMocks
    private DataService dataService = new DataServiceImpl();

    @Test
    public void testRetrieveInstellingenFull() throws IOException {
        Optional<String> instellingenXml = Optional.of(this.getFromResources("instellingen.xml"));

        when(this.xmlDownloadService.download()).thenReturn(instellingenXml);

        DataService dataService = new DataServiceImpl(xmlDownloadService);

        Optional<Instellingen> instellingen = dataService.retrieveInstellingen();

        assertTrue(instellingen.isPresent());
        assertTrue(instellingen.get().getInstelling().size() > 0);
    }

    @Test
    public void testSearchInstellingenFull() throws IOException {
        Optional<String> instellingenXml = Optional.of(this.getFromResources("instellingen.xml"));

        when(this.xmlDownloadService.download()).thenReturn(instellingenXml);

        DataServiceImpl dataService = new DataServiceImpl(xmlDownloadService);

        dataService.init(new Object());

        List<Instellingen.Instelling> instellingList = dataService.search(ZoekenOpType.INSTELLINGSNAAM, "ArtEZ");

        assertTrue(instellingList.size() > 0);
        assertEquals(1, instellingList.size());
    }

    @Test
    public void testSearchVestigingsplaatsFull() throws IOException {
        Optional<String> instellingenXml = Optional.of(this.getFromResources("instellingen.xml"));

        when(this.xmlDownloadService.download()).thenReturn(instellingenXml);

        DataServiceImpl dataService = new DataServiceImpl(xmlDownloadService);

        dataService.init(new Object());

        List<Instellingen.Instelling> instellingList = dataService.search(ZoekenOpType.GEMEENTENAAM, "AMSTERDAM");

        assertTrue(instellingList.size() > 0);
        assertEquals(7, instellingList.size());
    }

    @Test
    public void testSearchBothFull() throws IOException {
        Optional<String> instellingenXml = Optional.of(this.getFromResources("instellingen.xml"));

        when(this.xmlDownloadService.download()).thenReturn(instellingenXml);

        DataServiceImpl dataService = new DataServiceImpl(xmlDownloadService);

        dataService.init(new Object());

        List<Instellingen.Instelling> instellingList = dataService.search(ZoekenOpType.INSTELLINGSNAAM, "ArtEZ");
        instellingList = dataService.search(ZoekenOpType.GEMEENTENAAM, "ARNHEM", instellingList);

        assertTrue(instellingList.size() > 0);
        assertEquals(1, instellingList.size());
    }

    @Test
    public void testClearBothFull() throws IOException {
        Optional<String> instellingenXml = Optional.of(this.getFromResources("instellingen.xml"));

        when(this.xmlDownloadService.download()).thenReturn(instellingenXml);

        DataServiceImpl dataService = new DataServiceImpl(xmlDownloadService);

        dataService.init(new Object());

        List<Instellingen.Instelling> instellingList = dataService.all();

        assertTrue(instellingList.size() > 0);

        dataService.clear();

        instellingList = dataService.all();

        assertEquals(0, instellingList.size());
    }

    @Test
    public void testRetrieveByBrin() throws IOException {
        Optional<String> instellingenXml = Optional.of(this.getFromResources("instellingen.xml"));

        when(this.xmlDownloadService.download()).thenReturn(instellingenXml);

        DataServiceImpl dataService = new DataServiceImpl(xmlDownloadService);

        dataService.init(new Object());

        Optional<Instellingen.Instelling> result = dataService.retrieveBy("21PD");

        assertTrue(result.isPresent());

        Instellingen.Instelling instelling = result.get();

        assertEquals("wo", instelling.getSOORT());
        assertEquals("Utrecht", instelling.getPROVINCIE());
        assertEquals("3584 CS", instelling.getPOSTCODE());
    }

    private String getFromResources(String filename) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream(filename);

        return IOUtils.toString(inputStream, StandardCharsets.UTF_8);

    }
}
