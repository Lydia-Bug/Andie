//Skeleton Class for rotating an image 90 degrees to the left - needs documentation!

package cosc202.andie;
import java.awt.*;
import java.awt.image.*;

public class Rotate90 implements ImageOperation, java.io.Serializable {

    private int degreesOfRotation;

    Rotate90(int degreesOfRotation) {
        this.degreesOfRotation = degreesOfRotation;
    }

    public BufferedImage apply(BufferedImage input) {

        int height = input.getHeight();
        int width = input.getWidth();

        BufferedImage rotatedImage = new BufferedImage(width, height, input.getType());

        Graphics2D g = rotatedImage.createGraphics();

        g.rotate(Math.toRadians(degreesOfRotation), width/2, height/2);
        g.drawImage(input, null, 0, 0);


        return rotatedImage;
    }
    
}
