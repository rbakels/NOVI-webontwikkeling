package nl.novi.onderwijsinstellingen.definition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Path("/index")
public interface IndexResource {
    @GET
    void index(
            @Context HttpServletRequest request,
            @Context HttpServletResponse response) throws Exception;
}
