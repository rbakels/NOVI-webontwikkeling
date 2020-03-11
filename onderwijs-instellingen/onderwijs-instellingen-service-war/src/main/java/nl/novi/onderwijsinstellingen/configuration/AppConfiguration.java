package nl.novi.onderwijsinstellingen.configuration;

import nl.novi.onderwijsinstellingen.endpoint.DetailEndpoint;
import nl.novi.onderwijsinstellingen.endpoint.SearchEndpoint;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class AppConfiguration extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();

        classes.add(SearchEndpoint.class);
        classes.add(DetailEndpoint.class);

        return classes;
    }
}
