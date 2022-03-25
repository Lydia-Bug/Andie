package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * Class to apply a sharpening filter
 * 
 * A sharpening filter increases the contrast of each pixel compared to those around it
 * 
 * @author Callum Walker
 */

public class SharpenFilter implements ImageOperation, java.io.Serializable{
    
    private int sharpness;

    /**
     * Constructor for the class
     * 
     * @param sharpness The degree of sharpening
     * 
     */
    SharpenFilter(int sharpness) {
        this.sharpness = sharpness;
    }

    /**
     * Default contructor.
     * 
     * Sets the sharpness to 1 which will apply some sharpening
     * 
     */
    SharpenFilter() {
        sharpness = 1;
    }

    /**
     * Apply the sharpness filter to the image
     * 
     * This is done using a ConvolveOp class.
     * I have elected not to allow the user to adjust the size of
     * the kernal, instead the strength of the sharpness. These
     * values will likely change as it currently looks kinda bad
     * 
     */
    public BufferedImage apply(BufferedImage input) {

        float[] filterArray = {0, -sharpness, 0,
                               -sharpness, (4*sharpness)+1, -sharpness,
                               0, -sharpness, 0}; //This array defines the operation being down on each kernal. Total must be 1

        Kernel kernel = new Kernel(3,3, filterArray);
        ConvolveOp convOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);

        convOp.filter(input, output);

        return output;
    }
}
