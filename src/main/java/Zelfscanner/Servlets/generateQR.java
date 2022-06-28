package Zelfscanner.Servlets;

import Zelfscanner.Domeinmodel.TikkieAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

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
    @RolesAllowed("gebruiker")
    public Response generateQrCode(RequestData data) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();

        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(Date.from(Instant.now().plusSeconds(60)));

        RequestData body = new RequestData();
        body.description = data.description;
        body.expiryDateTime = nowAsISO;
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
