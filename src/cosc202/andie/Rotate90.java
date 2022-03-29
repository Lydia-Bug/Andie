//Skeleton Class for rotating an image 90 degrees to the left - needs documentation!

package cosc202.andie;
import java.awt.*;
import java.awt.image.*;


public class Rotate90 implements ImageOperation, java.io.Serializable {

    private int angle;

    Rotate90(int angle) {
        this.angle = angle;
    }

    public BufferedImage apply(BufferedImage input) {

        double rad = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rad));
        double cos = Math.abs(Math.cos(rad));

        int width = input.getWidth();
        int height = input.getHeight();

        int newWidth = (int) Math.floor(width * cos + height * sin);
        int newHeight = (int) Math.floor(width * sin + height * cos);

        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, input.getType());
        Graphics2D g = rotatedImage.createGraphics();

        g.translate((newWidth - width) / 2, (newHeight - height) / 2);
        g.rotate(rad, width / 2, height / 2);
        g.drawImage(input, null, 0, 0);

        return rotatedImage;
    } 
}
