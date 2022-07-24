import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import javax.management.RuntimeErrorException;

public class HttpUtils {

    public String sendGet(String url) {
        try {
            URI address = URI.create(url);
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(address).GET().build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            return response.body();
        } catch (Error | IOException | InterruptedException ex) {
            throw new RuntimeErrorException((Error) ex);
        }
    }
}