package cosc202.andie.Colours;

import java.awt.image.*;

import cosc202.andie.ImageOperation;

/**
 * <p>
 * ImageOperation to adjust the contrast of an image
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class AdjustContrast implements ImageOperation, java.io.Serializable {

    private double contrast;
    /**
     * <p>
     * Create a new AdjustContrast operation.
     * </p>
     */
    AdjustContrast(double contrast) {
        this.contrast = contrast;
    }

    /**
     * <p>
     * Adjust the contrast of an image.
     * </p>
     * 
     * <p>
     * 
     * </p>
     * 
     * @param input The image to be adjusted
     * @return The resulting adjusted image
     */
    public BufferedImage apply(BufferedImage input) {
  
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                //double brightness = 20;
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                int newR = Math.max(Math.min((int)Math.round((1+(contrast/100))*(r-127.5)+127.5),255),0);
                int newG = Math.max(Math.min((int)Math.round((1+(contrast/100))*(g-127.5)+127.5),255),0);
                int newB = Math.max(Math.min((int)Math.round((1+(contrast/100))*(b-127.5)+127.5),255),0);

                argb = (a << 24) | (newR << 16) | (newG << 8) | newB;
                input.setRGB(x, y, argb);
            }
        }
        
        return input;
    }
    
}