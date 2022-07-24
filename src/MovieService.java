import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MovieService {

    public void printBestMovies(List<Content> movies) {
        MovieService.printMovies(movies);
    }

    public void rateMovie(Scanner input, List<Content> movies) {
        String movieName = getMovieName(input);
        Content filmeFiltrado = movies.stream()
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

    public static void printMovies(List<Content> movies) {
        System.out.println(movies);
        for (Content movie : movies) {
            System.out.println(AnsiConstants.ANSI_PURPLE + "Titulo: " +
                    AnsiConstants.ANSI_RESET +
                    AnsiConstants.ANSI_GREEN + movie.getTitle() +
                    AnsiConstants.ANSI_RESET);

            System.out.println(AnsiConstants.ANSI_PURPLE + "Imagem: " +
                    AnsiConstants.ANSI_RESET +
                    movie.getImageUrl());

            System.out.println("-".repeat(20));
        }
    }
}
