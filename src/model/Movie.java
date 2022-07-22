package model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {


    @SerializedName("title")
    private String title;

    @SerializedName("imDbRating")
    private String rating;

    @SerializedName("image")
    private String image;

}
