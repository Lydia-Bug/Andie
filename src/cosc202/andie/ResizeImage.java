//Skeleton class for resizing image - documentation to be added

package cosc202.andie;
import java.awt.image.*;

public class ResizeImage implements ImageOperation, java.io.Serializable {

    private double newHeight;
    private double newWidth;

    ResizeImage(double newHeight, double newWidth) {
        this.newHeight = newHeight;
        this.newWidth = newWidth;
    }

    public BufferedImage apply(BufferedImage input) {
        return input;
    }
    
}

