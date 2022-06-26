package Zelfscanner.Servlets;

import Zelfscanner.Authentication.MySecurityContext;
import Zelfscanner.Domeinmodel.LoginInfo;
import Zelfscanner.Domeinmodel.LoginResponse;

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
        if (info.username.equals("Bram") && info.password.equals("bram")) {
            String token = MySecurityContext.generateToken("Bram");

            LoginInfo response = new LoginInfo();
            response.token = token;

            return Response.ok(response).build();
        } else {
            LoginInfo response = new LoginInfo();
            response.message = "Your username or password is incorrect!";
            return Response.status(401).entity(response).build();
        }
    }

    @POST
    @Path("/validat")
    @RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validate() {
        System.out.println("hij wordt gecalled");
        return Response.ok().build();
    }
}
