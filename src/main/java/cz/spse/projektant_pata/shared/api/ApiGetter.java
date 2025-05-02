package cz.spse.projektant_pata.shared.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


// This class is used to get names from the API
public class ApiGetter {
    private static final String API_URL = "https://randommer.io/api/Name?nameType=fullname&quantity=";
    private static final String API_KEY = "8b1a68c7456f419ca8ceac5f9354193f";

    private static final HttpClient client = HttpClient.newHttpClient();

    public static String[] getNames(int count) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + count))
                .header("X-Api-Key", API_KEY)
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String body = response.body()
                .replace("[", "")
                .replace("]", "")
                .replace("\"", "");

        return body.split(",");
    }

}
