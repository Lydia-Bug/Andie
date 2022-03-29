package cosc202.andie;
import java.awt.*;
import java.awt.image.*;

/**
 * <p>
 * ImageOperation to rotate an image. 
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Ella Taylor
 * @version 1.0
 */

public class Rotate implements ImageOperation {

    private int angle;
    /**
     * <p>
     * Create a new Rotate operation.
     * </p>
     * @param angle The angle by which to rotate the image.
     */
    Rotate(int angle) {
        this.angle = angle;
    }
    
    /**
     * <p>
     * Rotate an image. 
     * </p>
     * @param input The image to be rotated.
     * @return rotatedImage The rotated image. 
     */
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
