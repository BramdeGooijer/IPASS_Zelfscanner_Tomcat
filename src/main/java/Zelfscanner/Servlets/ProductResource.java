package Zelfscanner.Servlets;

import Zelfscanner.Authentication.MySecurityContext;
import Zelfscanner.Domeinmodel.LoginInfo;
import Zelfscanner.Domeinmodel.Product;
import Zelfscanner.Domeinmodel.ProductResponse;
import Zelfscanner.Domeinmodel.Winkel;
import javassist.NotFoundException;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/product")
public class ProductResource {
    Winkel winkel = Winkel.getWinkel();

    @GET
    @Path("{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getProductByBarcode(@PathParam("barcode") String barcode) throws NotFoundException {
        try {
            if (barcode.equals("IPASS-Admin")) {
                String token = MySecurityContext.generateToken("admin");

                LoginInfo response = new LoginInfo();
                response.message = "Admin";
                response.token = token;

                return Response.ok(response).build();
            }

            Product product = winkel.getProductByBarcode(barcode);

            ProductResponse response = new ProductResponse();
            response.prijs = product.getPrijs();
            response.naam = product.getNaam();
            response.beschrijving = product.getBeschrijving();
            response.barcode = product.getBarcode();
            response.foutmelding = "ok";

            return Response.ok(response).build();
        } catch (NotFoundException e) {
            ProductResponse response = new ProductResponse();
            response.foutmelding = e.getMessage();

            return Response.status(404).entity(response).build();
        }
    }

    @PUT
    @Path("/{barcode}")
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response wijzigPrijs(@PathParam("barcode") String barcode, ProductResponse info) {
        try {
            System.out.println(barcode);
            Product product = Winkel.getWinkel().getProductByBarcode(barcode);
            product.setPrijs(info.prijs);

            return Response.ok("de prijs is gewijzigd").build();
        } catch (NotFoundException e) {
            return Response.status(404).entity("product niet gevonden").build();
        } catch (Exception e) {
            return Response.status(401).entity("de prijs is niet gewijzigd er is iets mis gegaan").build();
        }
    }

    @POST
    @Path("/{barcode}")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response productToevoegen(@PathParam("barcode") String barcode, ProductResponse info) {
        try {
            Winkel.getWinkel().addProduct(info.naam, info.prijs, info.beschrijving, barcode);
            return Response.ok("Het product is toegevoegd!").build();
        } catch (IllegalArgumentException e) {
            return Response.status(401).entity("Illegal argument!").build();
        } catch (Exception e) {
            return Response.status(400).entity("something went wrong").build();
        }
    }

    @DELETE
    @Path("{barcode}")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response productVerwijderen(@PathParam("barcode") String barcode) {
        try {
            Winkel.getWinkel().verwijderProduct(barcode);
            return Response.ok("Het product is verwijderd!").build();
        } catch (NotFoundException e) {
            return Response.status(404).entity("Product niet gevonden!").build();
        } catch (Exception e) {
            return Response.status(400).entity("Something went wrong!").build();
        }
    }
}
