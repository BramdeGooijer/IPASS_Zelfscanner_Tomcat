package Zelfscanner.Authentication;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        System.out.println("Filter wordt uitgevoerd!");

        String authHeader = containerRequestContext.getHeaderString("Authorization");

        if (authHeader == null) {
            return;
        }

        String token = authHeader.replace("Bearer ", "");
        MyPrincipal principal = MySecurityContext.validateToken(token);

        boolean allowed = principal != null;

        if (allowed) {
            containerRequestContext.setSecurityContext(new MySecurityContext(principal));
        }
    }
}
