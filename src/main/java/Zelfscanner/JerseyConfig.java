package Zelfscanner;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("tikkieAPI")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("Zelfscanner.Servlets");
    }
}
