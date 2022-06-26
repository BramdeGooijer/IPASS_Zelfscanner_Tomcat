package Zelfscanner;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/restapi")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("Zelfscanner.Servlets");
        register(RolesAllowedDynamicFeature.class);
    }
}
