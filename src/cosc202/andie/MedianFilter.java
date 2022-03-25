package cosc202.andie;

import java.awt.image.BufferedImage;
import java.util.Arrays;
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

    /**
     * Only constructor needed.
     * Doesn't make sense to make adjustments to how 'median' the filter is 
     * 
     */
    MedianFilter() {}

    /**
     * Applies the filter to the image and returns the processed image
     * 
     * @param input The current image being edited
     * @return The post processed image
     * 
     */
    public BufferedImage apply(BufferedImage input){

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null); //Make sure the output has the same properties

        //Arrays to store the colour values
        Color[] pixelColour = new Color[9];
        int[] red = new int[9];
        int[] green = new int[9];
        int[] blue = new int[9];

        for(int i = 1; i < input.getWidth()-1; i++) {
            for(int j = 1; j < input.getHeight()-1; j++) {
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
