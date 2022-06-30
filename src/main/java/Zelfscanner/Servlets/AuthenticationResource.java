package Zelfscanner.Servlets;

import Zelfscanner.Authentication.MySecurityContext;
import Zelfscanner.Domeinmodel.LoginInfo;
import Zelfscanner.Domeinmodel.LoginResponse;
import Zelfscanner.Domeinmodel.Medewerker;
import Zelfscanner.Domeinmodel.Winkel;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class AuthenticationResource {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginInfo info) {
        for (Medewerker perMedewerker : Winkel.getWinkel().getAllMedewerkers()) {
            if (perMedewerker.getUsername().equals(info.username) && perMedewerker.getPassword().equals(info.password)) {
                String token = MySecurityContext.generateToken("BramIsIngelogd");

                LoginInfo response = new LoginInfo();
                response.token = token;

                return Response.ok(response).build();
            }
        }
        LoginInfo response = new LoginInfo();
        response.message = "Your username or password is incorrect!";
        return Response.status(401).entity(response).build();
    }

    @POST
    @Path("/validateToken")
    @RolesAllowed("gebruiker")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validate() {
        System.out.println("Validate token!");
        return Response.ok().build();
    }
}
