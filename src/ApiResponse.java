import lombok.Getter;
import model.ApiContent;
import model.Movie;

import java.util.List;

@Getter
public class ApiResponse {

    private List<Movie> items;

    private List<ApiContent> content;

}