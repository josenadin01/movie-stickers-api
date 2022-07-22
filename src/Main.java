import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import model.ApiContent;
import model.Movie;

public class Main {

    private static List<Movie> movies;
    private static List<ApiContent> content;

    public static void main(String[] args) throws IOException {

        var httpUtils = new HttpUtils();
        String url = "https://api.mocki.io/v2/549a5d8b";
        String json = httpUtils.sendGet(url).body();


        MovieService movieService = new MovieService();
        Scanner input = new Scanner(System.in);
        movies = movieService.getBestMovies();
        System.out.println(movies);
        int option = showMenu(input);
        switch (option) {
            case 1:
                movieService.printBestMovies(movies);
                break;
            case 2:
                input.nextLine();
                movieService.rateMovie(input, movies);
                break;
            case 3:
              //  stickerLoop(movies);
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
        System.out.println("3 - Gerar Stickers de filmes");
        System.out.print("Digite a opção: ");
        return sc.nextInt();
    }

    private static void stickerLoop(List<ApiContent> contents) throws IOException {
        var generator = new StickerGenerator();
        String imageUrl = "";
        String title = "";

        for (ApiContent content : contents) {
            imageUrl = content.getImageUrl();
            title = content.getTitle() + ".png";
            InputStream inputStream = new URL(imageUrl).openStream();
            generator.generateSticker(inputStream, title);
        }
    }

}