//Skeleton class for resizing image - documentation to be added

package cosc202.andie.Transformations;

import java.awt.*;
import java.awt.image.*;

import cosc202.andie.ImageOperation;

/**
 * <p>
 * ResizeImage class.
 * </p>
 * 
 * <p>
 * This class takes a scalefactor to resize the image width and height
 * and based on the given scalefactor uses a different
 * getscaledInstance method hint for a better image rendering.
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Hamzah Alansi
 * @version 1.0
 */

public class ResizeImage implements ImageOperation, java.io.Serializable {
    private double scaleFactor;

    /**
     * <p>
     * Constructor. Creates a new Resize operation.
     * </p>
     * 
     * @param scaleFactor double datafield determining the percentage of the image
     *                    to be scaled to.
     */
    public ResizeImage(double scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    /**
     * <p>
     * Uses the getScaledInstance method to control how scaled pixel values are
     * chosen and
     * to get a better picture if scaling up or down.
     * </p>
     * 
     * @param input The image to be resized.
     * @return resized input.
     */
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();

        int newHeight = (int) (height * scaleFactor / 100);
        int newWidth = (int) (width * scaleFactor / 100);
        Image img;

        if (scaleFactor / 100 <= 1) {
            img = input.getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING);

        } else {
            img = input.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        }

        input = new BufferedImage(newWidth, newHeight, input.getType());
        Graphics2D g = input.createGraphics();

        g.drawImage(img, 0, 0, null);
        g.dispose();

        return input;
    }

}
