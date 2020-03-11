package nl.novi.onderwijsinstellingen.configuration;

import nl.novi.onderwijsinstellingen.endpoint.IndexEndpoint;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/app")
public class AppConfiguration extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();

        classes.add(IndexEndpoint.class);

        return classes;
    }
}
