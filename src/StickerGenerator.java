import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class StickerGenerator {

    public void nameSticker(InputStream inputStream) throws MalformedURLException, IOException {
        Scanner input = new Scanner(System.in);
        // var stickerGenerator = new StickerGenerator();
        String stickerName = "";
        System.out.print("Digite o nome do seu sticker (tudo junto e sem acentos): ");
        stickerName = input.nextLine();
        input.close();
        generateSticker(inputStream, stickerName);
    }

    public void generateSticker(InputStream inputStream, String stickerName) throws IOException {

        // InputStream inputStream = new
        // URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_1.jpg").openStream();
        BufferedImage image = ImageIO.read(inputStream);

        int width = image.getWidth();
        int resizedHeight = image.getHeight() + 200;
        BufferedImage resizedImage = new BufferedImage(width, resizedHeight, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) resizedImage.getGraphics();
        graphics.drawImage(image, 0, 0, null);

        graphics.setColor(Color.CYAN);
        graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
        graphics.drawString("Topzera", (width / 2) - 100, resizedHeight - 100);

        ImageIO.write(resizedImage, "png", new File(stickerName));

    }
}
