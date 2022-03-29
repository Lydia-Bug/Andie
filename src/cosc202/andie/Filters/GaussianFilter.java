package cosc202.andie.Filters;

import java.awt.image.BufferedImage;

import cosc202.andie.ImageOperation;

/**
 * A class to apply a gaussian filter to an image
 * 
 * @author Callum Walker
 * 
 */
public class GaussianFilter implements ImageOperation, java.io.Serializable{
    
    private int radius;
    
    /**
     * Constructor for the GaussianFilter
     * 
     * @param radius Size of the kernel to apply the filter
     */
    GaussianFilter(int radius) {
        this.radius = radius;
    }

    /**
     * Default constructor incase no input is given
     * 
     * 3 is the minimum value that makes sense
     * 
     */
    GaussianFilter() {
        radius = 3;
    }

    public BufferedImage apply(BufferedImage input) {

        return input;
    }

}
