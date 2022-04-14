package cosc202.andie.Transformations;
import java.awt.image.*;
import java.awt.geom.*;

import cosc202.andie.ImageOperation;

/**
 * <p>
 * ImageOperation to flip an image horizontally or vertically. 
 * </p>
 * 
 * <p>
 * Segments of this code are adapted from Java Code Geeks, https://examples.javacodegeeks.com/desktop-java/awt/image/flipping-a-buffered-image/.
 * Citation: Kiourtzoglou, B. (2013, February 16). Flipping a buffered image. Examples Java Code Geeks. Retrieved April 14, 2022, from https://examples.javacodegeeks.com/desktop-java/awt/image/flipping-a-buffered-image/. 
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Ella Taylor
 * @version 1.0
 */
public class Flip implements ImageOperation {
    private boolean isVertical = true;

    /**
     * <p>
     * Constructor. Creates a new Flip operation.
     * </p>
     * @param clockwise Boolean datafield determining whether the image is flipped vertically or horizontally.
     */
    public Flip(boolean isVertical) {
        this.isVertical = isVertical;
    }

    /**
     * <p>
     * Uses AffineTransform.translate to invert either the height (for vertical flip) or width (for horizontal flip) coordinates for a given image.
     * This flips the image vertically or horizontally. 
     * </p>
     * @param input The image to be flipped.
     * @return input The flipped image. 
     */
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();
        AffineTransform tx;
        AffineTransformOp op;

        if(isVertical) {
            tx = AffineTransform.getScaleInstance(1, -1);
            tx.translate(0, -height);
            op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            input = op.filter(input, null);
        }
        else {
            tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-width, 0);
            op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            input = op.filter(input, null);
        }

        return input;
    }
    
}
