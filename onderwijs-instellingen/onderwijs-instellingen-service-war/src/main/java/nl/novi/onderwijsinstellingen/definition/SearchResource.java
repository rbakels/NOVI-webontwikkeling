package nl.novi.onderwijsinstellingen.definition;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.novi.onderwijsinstellingen.definition.domain.SearchRequest;
import nl.novi.onderwijsinstellingen.definition.domain.SearchResponse;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api
@Path("search")
public interface SearchResource {
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @ApiOperation(value = "Zoeken naar een instelling.", notes = "Zoeken naar een instelling.", response = SearchResponse.class)
    Response search(@Valid SearchRequest searchRequest);
}
