package cosc202.andie.Filters;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import cosc202.andie.ImageOperation;

import java.awt.Color;

/**
 * Applies a median filter
 * 
 * Check every pixel and its neighbouring pixels. Get the median
 * red, green and blue values and then writing a new image with these values * 
 * 
 * @author Callum Walker
 */

public class MedianFilter implements ImageOperation, java.io.Serializable{

    private int x, y, width, height;
    private boolean selection;
    /**
     * Only constructor needed.
     * Doesn't make sense to make adjustments to how 'median' the filter is 
     * @param x,y,width,height
     */
    MedianFilter(int x, int y, int width, int height, boolean selection) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.selection = selection;
    }

    /**
     * Applies the filter to the image and returns the processed image
     * 
     * @param input The current image being edited
     * @return The post processed image
     * 
     */
    public BufferedImage apply(BufferedImage input){
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
            this.x = 1;
            this.y = 1;
            this.width = input.getWidth()-2;
            this.height = input.getHeight()-2;
        }

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null); //Make sure the output has the same properties

        //Arrays to store the colour values
        Color[] pixelColour = new Color[9];
        int[] red = new int[9];
        int[] green = new int[9];
        int[] blue = new int[9];

        for(int i = this.x; i < this.x+this.width; i++) {
            for(int j = this.y; j < this.y+this.height; j++) {
                //I simply could not think of a better way to do this. Someone try fix???
                pixelColour[0] = new Color(input.getRGB(i - 1,j - 1));
                pixelColour[1] = new Color(input.getRGB(i - 1,j));
                pixelColour[2] = new Color(input.getRGB(i - 1,j + 1));
                pixelColour[3] = new Color(input.getRGB(i,j - 1));
                pixelColour[4] = new Color(input.getRGB(i,j));
                pixelColour[5] = new Color(input.getRGB(i,j + 1));
                pixelColour[6] = new Color(input.getRGB(i + 1,j - 1));
                pixelColour[7] = new Color(input.getRGB(i + 1,j));
                pixelColour[8] = new Color(input.getRGB(i + 1,j + 1));

                //Grab the colour values
                for(int k = 0; k < 9; k++) {
                    red[k] = pixelColour[k].getRed();
                    green[k] = pixelColour[k].getGreen();
                    blue[k] = pixelColour[k].getBlue();
                }

                //Sorted so the middle value will be the median
                Arrays.sort(red);
                Arrays.sort(green);
                Arrays.sort(blue);

                output.setRGB(i, j, new Color(red[4],green[4],blue[4]).getRGB());
            }
        }

        return output;
    }
    
}
