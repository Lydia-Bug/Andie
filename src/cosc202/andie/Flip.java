package cosc202.andie;
import java.awt.*;
import java.awt.image.*;

public class Flip implements ImageOperation {
    private boolean isVertical = true;

    public Flip(boolean isVertical) {
        this.isVertical = isVertical;
    }

    public BufferedImage apply(BufferedImage input) {
        Graphics2D g = input.createGraphics();
        int width = input.getWidth();
        int height = input.getHeight();

        if(isVertical) {
            g.drawImage(input, width, 0, width, -1 * height, null);
        }
        else {
            g.drawImage(input, width, 0, -1 * width, height, null);
        }
        return input;
    }
    
}
