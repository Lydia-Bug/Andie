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
    private int x, y, width, height;
    private boolean selection;
    /**
     * <p>
     * Create a new AdjustContrast operation.
     * </p>
     */
    AdjustContrast(double contrast, int x, int y, int width, int height, boolean selection) {
        this.contrast = contrast;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.selection = selection;
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
System.out.println(x + " " + y +" "+ width + " " + height);
        for (int y = this.y; y < this.height+this.y; ++y) {
            for (int x = this.x; x < this.width+this.x; ++x) {
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
