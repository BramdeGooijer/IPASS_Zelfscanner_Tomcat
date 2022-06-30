package Zelfscanner.Servlets;

import Zelfscanner.Domeinmodel.TransactieInfo;
import Zelfscanner.Domeinmodel.Winkel;
import com.azure.core.annotation.Post;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/transactie")
public class TransactionResource {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTransaction(TransactieInfo info) {
        try {
            Winkel.getWinkel().addTransactie(info.totaalAantal, info.totaalPrijs);

            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(401).entity(e).build();
        }
    }
}
