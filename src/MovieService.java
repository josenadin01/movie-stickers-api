import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import model.ApiContent;
import model.Movie;
public class MovieService {

    public void printBestMovies(List<Movie> movies) {
        MovieService.printMovies(movies);
    }

    public List<Movie> getBestMovies() {
        HttpUtils httpUtils = new HttpUtils();

        return httpUtils.sendMovieGet();
    }

    public void rateMovie(Scanner input, List<Movie> movies) {
        String movieName = getMovieName(input);
        Movie filmeFiltrado = movies.stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(movieName))
                .findFirst()
                .orElse(null);
        if (Objects.nonNull(filmeFiltrado)) {
            System.out.print("Digite a nota do filme: ");
            int rate = input.nextInt();
            System.out.println("Você avaliou " + movieName + " com nota " + rate);
        } else {
            System.out.println("Esse filme não existe");
            rateMovie(input, movies);
        }
    }

    private static String getMovieName(Scanner sc) {
        System.out.print("Digite o nome do filme que deseja avaliar: ");
        return sc.nextLine();
    }

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

