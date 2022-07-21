import java.util.List;
public class MovieUtils {

    public static void printMovies(List<Movie> movies) {
        System.out.println(movies);
        for (Movie movie : movies) {
            System.out.println(AnsiConstants.ANSI_PURPLE + "Titulo: " +
                    AnsiConstants.ANSI_RESET +
                    AnsiConstants.ANSI_GREEN + movie.getTitle() +
                    AnsiConstants.ANSI_RESET);

            System.out.println(AnsiConstants.ANSI_PURPLE + "Imagem: "+
                    AnsiConstants.ANSI_RESET +
                    movie.getImage());

            System.out.println(AnsiConstants.ANSI_PURPLE + "Avaliação: " +
            parseRating(movie.getRating()) + " ("+movie.getRating()+")" +
                    AnsiConstants.ANSI_RESET);

            System.out.println("-".repeat(20));
        }
    }

    private static String parseRating(String rating_p) {
        double rating = Double.parseDouble(rating_p);
        rating = Math.floor(rating);
        StringBuilder stars = new StringBuilder();
        stars.append(AnsiConstants.ANSI_YELLOW);
        for (int i = 0; i < 10; i++) {
            if (i < rating) {
                stars.append(AnsiConstants.ANSI_FILLED_STAR);
                continue;
            }
            stars.append(AnsiConstants.ANSI_EMPTY_STAR);
        }
        stars.append(AnsiConstants.ANSI_RESET);
        return stars.toString();
    }
}

