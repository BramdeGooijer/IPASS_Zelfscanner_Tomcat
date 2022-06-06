package Zelfscanner.Servlets;

import Zelfscanner.Domeinmodel.TikkieAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Path("generateQR")
public class generateQR {
    private static class RequestData {
        public String description;
        public String expiryDateTime;
        public int amountInCents;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response generateQrCode(RequestData data) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();

        RequestData body = new RequestData();
        body.description = data.description;
        body.expiryDateTime = data.expiryDateTime;
        body.amountInCents = data.amountInCents;

        ObjectMapper mapper = new ObjectMapper();
        HttpRequest req = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .header("X-App-Token", TikkieAPI.getAppToken())
                .header("API-KEY", TikkieAPI.getApiKey())
                .uri(new URI("https://api-sandbox.abnamro.com/v2/tikkie/idealqrs"))

                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(body)))
                .build();

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        return Response.ok(resp.body()).build();
    }
}
