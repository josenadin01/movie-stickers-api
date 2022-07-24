import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        var http = new HttpUtils();
        MovieService movieService = new MovieService();
        Scanner input = new Scanner(System.in);

        ContentExtractor imdbExtractor = new ImdbContentExtractor();
        String imdbEndpoint = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        String imdbJson = http.sendGet(imdbEndpoint);
        List<Content> imdbContent = imdbExtractor.extractContents(imdbJson);

        ContentExtractor nasaExtractor = new NasaContentExtractor();
        String nasaEndpoint = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";
        String nasaJson = http.sendGet(nasaEndpoint);
        List<Content> nasaContent = nasaExtractor.extractContents(nasaJson);

        int option = showMenu(input);
        switch (option) {
            case 1:
                movieService.printBestMovies(imdbContent);
                break;
            case 2:
                input.nextLine();
                movieService.rateMovie(input, imdbContent);
                break;
            case 3:
                stickerLoop(nasaContent);
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
        System.out.println("3 - Gerar Stickers da NASA");
        System.out.print("Digite a opção: ");
        return sc.nextInt();
    }

    private static void stickerLoop(List<Content> content) throws IOException {
        var generator = new StickerGenerator();

        for (int i = 0; i < 3; i++) {
            Content conteudo = content.get(i);

            InputStream inputStream = new URL(conteudo.getImageUrl()).openStream();
            String nomeArquivo = "assets/" + conteudo.getTitle() + ".png";

            generator.generateSticker(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitle());
            System.out.println();
        }
    }
}

// private static List<Movie> movies;
// private static List<ApiContent> content = new ArrayList<>();

// public static void main(String[] args) throws IOException {
// var jsonParser = new JsonParser();
// var httpUtils = new HttpUtils();

// //String url = "https://api.mocki.io/v2/549a5d8b";
// String url =
// "https://api.nasa.gov/planetary/apod?api_key=EWX6DPnMqNWtoRNOEYhheQjbE224HfF2AQ8dHGNl&start_date=2022-06-12&end_date=2022-06-14";
// String json = httpUtils.sendGet(url).body();
// content = jsonParser.parse(json, ApiResponse.class).getContent();

// MovieService movieService = new MovieService();
// Scanner input = new Scanner(System.in);
// movies = movieService.getBestMovies();
// System.out.println(content);
// int option = showMenu(input);
// switch (option) {
// case 1:
// movieService.printBestMovies(movies);
// break;
// case 2:
// input.nextLine();
// movieService.rateMovie(input, movies);
// break;
// case 3:
// // stickerLoop(content);
// break;
// default:
// System.out.println("Essa opção não existe");
// main(args);
// break;
// }
// input.close();
// }

// private static int showMenu(Scanner sc) {
// System.out.println("Escolha uma das opções abaixo");
// System.out.println("1 - Ver os melhores filmes");
// System.out.println("2 - Avaliar um filme");
// System.out.println("3 - Gerar Stickers de filmes");
// System.out.print("Digite a opção: ");
// return sc.nextInt();
// }

// private static void stickerLoop(List<ApiContent> contents) throws IOException
// {
// var generator = new StickerGenerator();
// String imageUrl = "";
// String title = "";

// for (ApiContent content : contents) {
// imageUrl = content.getImageUrl();
// title = content.getTitle() + ".png";
// InputStream inputStream = new URL(imageUrl).openStream();
// generator.generateSticker(inputStream, title);
// }
// }
