import com.google.gson.Gson;

public class JsonParser {

    public static <T> T parse(String json, Class<T> clazz) {
        return new Gson().fromJson(json, clazz);
    }
}