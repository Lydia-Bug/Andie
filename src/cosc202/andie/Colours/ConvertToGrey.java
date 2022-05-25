package cosc202.andie.Colours;

import java.awt.image.*;

import cosc202.andie.ImageOperation;

/**
 * <p>
 * ImageOperation to convert an image from colour to greyscale.
 * </p>
 * 
 * <p>
 * The images produced by this operation are still technically colour images,
 * in that they have red, green, and blue values, but each pixel has equal
 * values for red, green, and blue giving a shade of grey.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ConvertToGrey implements ImageOperation, java.io.Serializable {
    private int x, y, width, height;
    private boolean selection;
    /**
     * <p>
     * Create a new ConvertToGrey operation.
     * </p>
     * @param x,y,width,height
     */
    ConvertToGrey(int x, int y, int width, int height, boolean selection) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.selection = selection;
        
    }

    /**
     * <p>
     * Apply greyscale conversion to an image.
     * </p>
     * 
     * <p>
     * The conversion from red, green, and blue values to greyscale uses a 
     * weighted average that reflects the human visual system's sensitivity 
     * to different wavelengths -- we are most sensitive to green light and 
     * least to blue.
     * </p>
     * 
     * @param input The image to be converted to greyscale
     * @return The resulting greyscale image.
     */
    public BufferedImage apply(BufferedImage input) {
        if(selection){
            if(this.x > input.getWidth() || this.y > input.getHeight()){
                return input;
            }
            if (this.x + this.width > input.getWidth()) {
                width = input.getWidth() - x;
            }
            if (this.y + this.height > input.getHeight()) {
                height = input.getHeight() - y;
            }
            if (this.x < 0) {
                this.x = 0;
            }
            if (this.y < 0) {
                this.y = 0;
            }
        }else{
            this.x = 0;
            this.y = 0;
            this.width = input.getWidth();
            this.height = input.getHeight();
        }

        for (int y = this.y; y < this.height+this.y; ++y) {
            for (int x = this.x; x < this.width+this.x; ++x) {
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                int grey = (int) Math.round(0.3*r + 0.6*g + 0.1*b);

                argb = (a << 24) | (grey << 16) | (grey << 8) | grey;
                input.setRGB(x, y, argb);
            }
        }
        
        return input;
    }
    
}
