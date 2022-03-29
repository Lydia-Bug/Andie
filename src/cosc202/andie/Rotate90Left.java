//Skeleton Class for rotating an image 90 degrees to the left

package cosc202.andie;
import java.awt.image.*;

public class Rotate90Left implements ImageOperation, java.io.Serializable {

    Rotate90Left() {

    }

    public BufferedImage apply(BufferedImage input) {
        return input;
    }
    
}
