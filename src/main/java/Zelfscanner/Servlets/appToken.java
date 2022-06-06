package Zelfscanner.Servlets;

import Zelfscanner.Domeinmodel.TikkieAPI;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Path("appToken")
public class appToken {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateAppToken() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();

        ObjectMapper mapper = new ObjectMapper();
        HttpRequest req = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .header("API-KEY", TikkieAPI.getApiKey())
                .uri(new URI("https://api-sandbox.abnamro.com/v2/tikkie/sandboxapps"))

                .POST(HttpRequest.BodyPublishers.ofString(""))
                .build();

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        return Response.ok(resp.body()).build();
    }
}
