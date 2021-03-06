package cosc202.andie.Transformations;

import java.awt.*;
import java.awt.image.BufferedImage;
import cosc202.andie.ImageOperation;

/**
 * <p>
 * ImageCrop class.
 * </p>
 * 
 * <p>
 * This class takes the bounds of the mouse selection from ImagePanel
 * to crop the image within those dimensions of the selection bounds.
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Hamzah Alansi
 * @version 1.0
 */

public class ImageCrop implements ImageOperation, java.io.Serializable   {
    //BufferedImage croppedImage;
    int x, y, width, height;

    /**
     * <p>
     * Constructor. Creates a new ImageCrop operation.
     * </p>
     * 
     * @param x,y,width,height
     */
    public ImageCrop(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * <p>
     * Uses the getSubImage method to create a new Image of certain dimensions
     * decided by mouse selection
     *
     * </p>
     * 
     * @param input The image to be Cropped.
     * @return crop input.
     */
    @Override
    public BufferedImage apply(BufferedImage input) {
        
        //This allows an out of bounds crop in a macros file to not break the code
        if(x > input.getWidth() || y > input.getHeight()){
            return input;
        }

        BufferedImage croppedImage;

        if (x + width > input.getWidth()) {
            width = input.getWidth() - x;
        }
        if (y + height > input.getHeight()) {
            height = input.getHeight() - y;
        }
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        croppedImage = input.getSubimage(x, y, width, height);
        Graphics2D g = croppedImage.createGraphics();
        g.drawImage(croppedImage, 0, 0, null);
        g.dispose();

        return croppedImage;
    }
}