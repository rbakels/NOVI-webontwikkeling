package nl.novi.onderwijsinstellingen.endpoint;

import nl.novi.onderwijsinstellingen.definition.IndexResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestScoped
public class IndexEndpoint implements IndexResource {
    private static final Logger logger = LoggerFactory.getLogger(IndexEndpoint.class);

    public IndexEndpoint() {
        // Intended as empty.
    }

    @Override
    public void index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("Rendering index page.");

        request.getParameter()
        request.getRequestDispatcher("/jsp/index.jsp")
                .forward(request, response);
    }
}
