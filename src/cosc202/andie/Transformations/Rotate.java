package cosc202.andie.Transformations;
import java.awt.geom.*;
import java.awt.image.*;

import cosc202.andie.ImageOperation;

/**
 * <p>
 * ImageOperation to rotate an image. 
 * </p>
 * 
 * <p>
 * Segments of this code are adapted from DelftStack, https://www.delftstack.com/howto/java/java-rotate-image/.
 * Citation: Yadav, R. (2021, September 6). Rotate an image in Java. Delft Stack. Retrieved April 14, 2022, from https://www.delftstack.com/howto/java/java-rotate-image/ 
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

    private boolean clockwise;
    /**
     * <p>
     * Constructor. Creates a new Rotate operation.
     * </p>
     * @param clockwise Boolean datafield determining whether the image is rotated clockwise or anticlockwise.
     */
    Rotate(boolean clockwise) {
        this.clockwise = clockwise;
    }
    
    /**
     * <p>
     * Uses the AffineTransform class to rotate and then translate an image by 90 degrees (clockwise or anticlockwise). 
     * </p>
     * @param input The image to be rotated.
     * @return input The rotated image. 
     */
    public BufferedImage apply(BufferedImage input) {
        final double DEGREES_90 = Math.PI / 2;
        int width = input.getWidth();
        int height = input.getHeight();
        double offset = (width - height) / 2;
        AffineTransform tx = new AffineTransform();

        if(clockwise) {
            tx.rotate(DEGREES_90, width / 2, height / 2);
            tx.translate(offset, offset);
        }
        else {
            tx.rotate(-DEGREES_90, width / 2, height / 2);
            tx.translate(-offset, -offset);    
        }

        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        input = op.filter(input, null);

        return input;
    }
}
