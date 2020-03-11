package nl.novi.onderwijsinstellingen.endpoint;

import nl.novi.onderwijsinstellingen.definition.SearchResource;
import nl.novi.onderwijsinstellingen.definition.domain.SearchForRequestObject;
import nl.novi.onderwijsinstellingen.definition.domain.SearchRequest;
import nl.novi.onderwijsinstellingen.definition.domain.onderwijsinstellingen.Instellingen;
import nl.novi.onderwijsinstellingen.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
public class SearchEndpoint implements SearchResource {
    private static final Logger logger = LoggerFactory.getLogger(SearchEndpoint.class);

    private DataService dataService;

    public SearchEndpoint() {
        // Empty constructor is intended.
    }

    @Inject
    public SearchEndpoint(DataService dataService) {
        this.dataService = dataService;
    }

    @Override
    public Response search(SearchRequest searchRequest) {
        logger.info("Search request started with parameters {}", searchRequest);

        List<SearchForRequestObject> searchList = searchRequest.getSearch();
        List<Instellingen.Instelling> instellingenList = this.dataService.all();

        for (SearchForRequestObject searchFor : searchList) {
            instellingenList = this.dataService.search(searchFor.getZoekOpdrachtType(), searchFor.getZoekWaarde(), instellingenList);
        }

        return Response.ok(instellingenList).build();
    }
}
