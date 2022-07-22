import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

import javax.management.RuntimeErrorException;

import model.ApiContent;
import model.Movie;

public class HttpUtils {

    public HttpResponse<String> sendGet(String url) {
        try {
            URI address = URI.create(url);
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(address).GET().build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Error | IOException | InterruptedException ex) {
            throw new RuntimeErrorException((Error) ex);
        }
    }

    public List<Movie> sendMovieGet() {
        try {
            String url = "https://api.mocki.io/v2/549a5d8b";
            String body = sendGet(url).body();
            return JsonParser.parse(body, ApiResponse.class).getItems();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}