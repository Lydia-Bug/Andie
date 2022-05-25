package cosc202.andie.Draw;

import java.awt.image.*;
import java.awt.geom.*;
import java.awt.*;
import cosc202.andie.ImageOperation;
import cosc202.andie.ImagePanel;


/**
 * <p>
 * ImageOperation to draw rectangle.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Ella Taylor
 * @version 1.0
 */
public class DrawRectangle implements ImageOperation, java.io.Serializable  {

    /**
     * <p>
     * Constructor. Creates a new DrawRectangle operation.
     * </p>
     * 
     */
    Color outline, fill;
    int thickness;
    boolean isFilled;
    ImagePanel target;

    public DrawRectangle(Color outline, Color fill, int thickness, boolean isFilled, ImagePanel target) {
        this.outline = outline;
        this.fill = fill;
        this.thickness = thickness;
        this.isFilled = isFilled;
        this.target = target;
    }

    /**
     * <p>
     * Draws rectangle on image.
     * </p>
     */
    public BufferedImage apply(BufferedImage input) {
        Graphics2D g2 = input.createGraphics();
        Rectangle2D m = target.GetMouseRectangle();
        g2.setStroke(new BasicStroke(thickness));
        
        g2.setPaint(outline);
        g2.drawRect((int) m.getX(), (int) m.getY(), (int) m.getWidth(), (int) m.getHeight());

        if(isFilled) {
            g2.setPaint(fill);
            g2.fillRect((int) m.getX(), (int) m.getY(), (int) m.getWidth(), (int) m.getHeight());
        }

        g2.dispose();
        return input;
    }
    
}

