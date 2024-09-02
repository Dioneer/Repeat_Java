package pegas.http;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

import static java.net.http.HttpRequest.BodyPublishers.ofFile;

public class HttpClientRunner {
    public static void main(String[] args) throws FileNotFoundException {
        var httpClient = java.net.http.HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
        HttpRequest request =HttpRequest.newBuilder().uri(URI.create("http://localhost:8082"))
                .headers("content-type","application/json")
                .POST(ofFile(Path.of("src/main/resources/example.json")))
                .build();
        try {
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response);
            System.out.println(response.body());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
