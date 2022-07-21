import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static List<Movie> movies;

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        movies = getBestMovies();
        System.out.println(movies);
        int option = showMenu(input);
        switch (option) {
            case 1:
                printBestMovies();
                break;
            case 2:
                input.nextLine();
                rateMovie(input);
                break;
            case 3:
                stickerLoop(movies);
                break;
            default:
                System.out.println("Essa opção não existe");
                main(args);
                break;
        }
        input.close();
    }

    private static int showMenu(Scanner sc) {
        System.out.println("Escolha uma das opções abaixo");
        System.out.println("1 - Ver os melhores filmes");
        System.out.println("2 - Avaliar um filme");
        System.out.println("3 - Gerar Stickers");
        System.out.print("Digite a opção: ");
        return sc.nextInt();
    }

    private static void printBestMovies() {
        MovieUtils.printMovies(movies);
    }

    private static List<Movie> getBestMovies() {
        try {
            String url = "https://api.mocki.io/v2/549a5d8b";
            String body = HttpUtils.sendGet(url).body();
            return JsonParser.parse(body, ApiResponse.class).getItems();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private static void rateMovie(Scanner input) {
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
            rateMovie(input);
        }
    }

    private static String getMovieName(Scanner sc) {
        System.out.print("Digite o nome do filme que deseja avaliar: ");
        return sc.nextLine();
    }

    private static void stickerLoop(List<Movie> movies) throws IOException {
        var generator = new StickerGenerator();
        String imageUrl = "";
        String title = "";

        for (Movie movie : movies) {
            imageUrl = movie.getImage();
            title = movie.getTitle().replaceAll("\\s", "");
            InputStream inputStream = new URL(imageUrl).openStream();
            generator.generateSticker(inputStream, title);
        }
    }

}