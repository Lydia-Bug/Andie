package cosc202.andie.Draw;

import java.awt.image.*;
import java.awt.*;
import java.awt.geom.*;
import cosc202.andie.ImageOperation;
import cosc202.andie.ImagePanel;


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
public class DrawOval implements ImageOperation, java.io.Serializable   {

    /**
     * <p>
     * Constructor. Creates a new Flip operation.
     * </p>
     * @param clockwise Boolean datafield determining whether the image is flipped vertically or horizontally.
     */
    Color outline, fill;
    int thickness;
    boolean isFilled;
    ImagePanel target;


    public DrawOval(Color outline, Color fill, int thickness, boolean isFilled, ImagePanel target) {
        this.outline = outline;
        this.fill = fill;
        this.thickness = thickness;
        this.isFilled = isFilled;
        this.target = target;
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
        Rectangle2D m = target.GetMouseRectangle();
        Graphics2D g2 = input.createGraphics();
        g2.setStroke(new BasicStroke(thickness));
    
        g2.setPaint(outline);
        g2.drawOval((int) m.getX(), (int) m.getY(), (int) m.getWidth(), (int) m.getHeight());

        if (isFilled) {
            g2.setPaint(fill);
            g2.fillOval((int) m.getX(), (int) m.getY(), (int) m.getWidth(), (int) m.getHeight());
        }

        g2.dispose();
        return input;     
    }
    
}
