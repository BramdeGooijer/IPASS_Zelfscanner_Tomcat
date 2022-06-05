package Zelfscanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TikkieQrRequest {
    private static class RequestData {
        public String description;
        public String expiryDateTime;
        public int amountInCents;
    }

    public static void main(String[] args) throws Exception{
        HttpClient client = HttpClient.newBuilder().build();

        RequestData body = new RequestData();
        body.description = "Testing the qr code in java";
        body.expiryDateTime = "2022-06-07T12:00:00.000Z";
        body.amountInCents = 1;

        ObjectMapper mapper = new ObjectMapper();
        HttpRequest req = HttpRequest.newBuilder()
                .header("X-App-Token", "a28b5137-b598-4a66-9b72-dc03d3ae7273")
                .header("API-KEY", "nru7vQI56ZAoUCfeHVJINgVeUeDLqFsa")
                .header("Content-Type", "application/json")
                .uri(new URI("https://api-sandbox.abnamro.com/v2/tikkie/idealqrs"))

                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(body)))
                .build();

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println(resp.body());
    }
}
