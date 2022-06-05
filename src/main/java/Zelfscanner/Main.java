package Zelfscanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    private static class RequestData {
        public String description;
        public String expiryDate;
        public String referenceId;
    }
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newBuilder().build();

        RequestData body = new RequestData();
//        body.description = "test payment";
//        body.expiryDate = "2022-02-05";
//        body.referenceId = "test1";

        ObjectMapper mapper = new ObjectMapper();
        HttpRequest req = HttpRequest.newBuilder()
                .header("API-KEY", "nru7vQI56ZAoUCfeHVJINgVeUeDLqFsa")
                .header("Content-Type", "application/json")
                .uri(new URI("https://api-sandbox.abnamro.com/v2/tikkie/sandboxapps"))

                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(body)))
                .build();

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println(resp.body());
    }
}
