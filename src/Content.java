public class Content {

    private final String titulo;
    private final String urlImagem;

    public Content(String titulo, String urlImagem) {
        this.titulo = titulo;
        this.urlImagem = urlImagem;
    }

    public String getTitle() {
        return titulo;
    }

    public String getImageUrl() {
        return urlImagem;
    }

}