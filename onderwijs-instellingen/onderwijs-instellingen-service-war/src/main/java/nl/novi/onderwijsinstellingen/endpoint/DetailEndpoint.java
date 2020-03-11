package nl.novi.onderwijsinstellingen.endpoint;

import nl.novi.onderwijsinstellingen.definition.DetailResource;
import nl.novi.onderwijsinstellingen.definition.domain.onderwijsinstellingen.Instellingen;
import nl.novi.onderwijsinstellingen.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Optional;

@RequestScoped
public class DetailEndpoint implements DetailResource {
    private static final Logger logger = LoggerFactory.getLogger(DetailEndpoint.class);

    private DataService dataService;

    public DetailEndpoint() {
        // Intended as empty.
    }

    @Inject
    public DetailEndpoint(DataService dataService) {
        this.dataService = dataService;
    }

    @Override
    public Response retrieve(String brin) {
        logger.info("Retrieving information for BRIN {}", brin);

        Optional<Instellingen.Instelling> instellingOptional = this.dataService.retrieveBy(brin);

        if (instellingOptional.isPresent()) {
            return Response.ok(instellingOptional.get()).build();
        } else {
            return Response.status(404).build();
        }

    }
}
