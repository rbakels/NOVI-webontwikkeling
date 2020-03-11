package nl.novi;

import nl.novi.onderwijsinstellingen.service.impl.XmlDownloadServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class XmlDownloadServiceTest {
    @Test
    public void testOK() {
        XmlDownloadServiceImpl xmlDownloadService = new XmlDownloadServiceImpl("http://localhost:8080/onderwijsinstellingen.xml");

        Optional<String> result = xmlDownloadService.download();

        assertTrue(result.isPresent());

        String response = result.get();

        assertTrue(StringUtils.isNotEmpty(response));
        assertTrue(response.contains("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
        assertTrue(response.contains("instellingen"));
        assertTrue(response.contains("instelling"));
        assertTrue(response.contains("INSTELLINGSNAAM"));
    }

    @Test
    public void testNotOK() {
        XmlDownloadServiceImpl xmlDownloadService = new XmlDownloadServiceImpl("http://localhost:8080/onderwijsinstellingenNOK.xml");

        Optional<String> result = xmlDownloadService.download();

        assertFalse(result.isPresent());
    }
}
