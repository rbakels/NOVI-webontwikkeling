package nl.novi.onderwijsinstellingen.definition;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.novi.onderwijsinstellingen.definition.domain.DetailResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api
@Path("detail/by/{brin}")
public interface DetailResource {
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @GET
    @ApiOperation(value = "Informatie opvragen over een instelling.", notes = "Informatie opvragen over een instelling.", response = DetailResponse.class)
    Response retrieve(@PathParam("brin") String brin);
}
