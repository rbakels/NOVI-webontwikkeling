package nl.novi.onderwijsinstellingen.service.impl;

import nl.novi.onderwijsinstellingen.definition.domain.ZoekenOpType;
import nl.novi.onderwijsinstellingen.definition.domain.onderwijsinstellingen.Instellingen;
import nl.novi.onderwijsinstellingen.service.DataService;
import nl.novi.onderwijsinstellingen.service.XmlDownloadService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.xml.bind.JAXB;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class DataServiceImpl implements DataService {
    private static final Logger logger = LoggerFactory.getLogger(DataServiceImpl.class);

    private XmlDownloadService xmlDownloadService;

    private List<Instellingen.Instelling> instellingen;

    public DataServiceImpl() {
        this.instellingen = Collections.emptyList();
    }

    @Inject
    public DataServiceImpl(XmlDownloadService xmlDownloadService) {
        this.xmlDownloadService = xmlDownloadService;
        this.instellingen = Collections.emptyList();
    }

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        logger.info("Initializing data in memory.");

        this.initialize();
    }

    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object init) {
        this.clear();
    }

    @Override
    public Optional<Instellingen> retrieveInstellingen() {
        Optional<String> sourceXml = this.xmlDownloadService.download();

        if (sourceXml.isPresent() && StringUtils.isNotEmpty(sourceXml.get())) {
            String xmlData = sourceXml.get();

            return Optional.of(JAXB.unmarshal(new StringReader(xmlData),
                    Instellingen.class));
        } else {
            logger.error("Retrieved XML Data is empty. Cannot process.");

            return Optional.empty();
        }
    }

    @Override
    public void clear() {
        logger.info("Clearing data from memory storage.");

        this.instellingen = Collections.emptyList();

        logger.info("Cleared data.");
    }

    @Override
    public void reset() {
        this.clear();
        this.initialize();
    }

    @Override
    public void initialize() {
        Optional<Instellingen> instellingenOptional = this.retrieveInstellingen();

        if (instellingenOptional.isPresent()) {
            Instellingen instellingen = instellingenOptional.get();

            this.instellingen = instellingen.getInstelling();

            logger.info("Initialized data. Retrieved {} items.", this.instellingen.size());
        } else {
            logger.error("No response from source server.");

            this.instellingen = Collections.emptyList();
        }
    }

    @Override
    public List<Instellingen.Instelling> all() {
        return this.instellingen;
    }

    /**
     * Retrieve a Instelling by a given BRIN.
     *
     * @param brin
     * @return Instelling.
     */
    @Override
    public Optional<Instellingen.Instelling> retrieveBy(String brin) {
        Stream<Instellingen.Instelling> instellingStream = this.all().stream();
        instellingStream = instellingStream.filter(p -> (p.getBRI().equals(brin)));

        List<Instellingen.Instelling> result = instellingStream.collect(Collectors.toList());

        if (!result.isEmpty()) {
            return Optional.of(result.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Instellingen.Instelling> search(ZoekenOpType type, String searchFor) {
        return this.search(type, searchFor, this.all());
    }

    @Override
    public List<Instellingen.Instelling> search(ZoekenOpType type, String searchFor, List<Instellingen.Instelling> searchIn) {
        Stream<Instellingen.Instelling> instellingStream = searchIn.stream();

        switch (type) {
            case INSTELLINGSNAAM:
                instellingStream = instellingStream.filter(p -> (p.getINSTELLINGSNAAM().toLowerCase().startsWith(searchFor.toLowerCase())));

                break;
            case GEMEENTENAAM:
                instellingStream = instellingStream.filter(p -> (p.getGEMEENTENAAM().toLowerCase().startsWith(searchFor.toLowerCase())));

                break;
        }

        return instellingStream.collect(Collectors.toList());
    }
}
