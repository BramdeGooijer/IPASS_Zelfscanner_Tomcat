package Zelfscanner.Servlets;

import Zelfscanner.Domeinmodel.Product;
import Zelfscanner.Domeinmodel.ProductResponse;
import Zelfscanner.Domeinmodel.Winkel;
import javassist.NotFoundException;

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
            Product product = winkel.getProductByBarcode(barcode);

            ProductResponse response = new ProductResponse();
            response.prijs = product.getPrijs();
            response.naam = product.getNaam();
            response.beschrijving = product.getBeschrijving();
            response.barcode = product.getBarcode();

            return Response.ok(response).build();
        } catch (NotFoundException e) {
            ProductResponse response = new ProductResponse();
            response.foutmelding = e.getMessage();

            return Response.status(404).entity(response).build();
        }
    }
}
