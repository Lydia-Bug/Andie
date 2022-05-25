package cosc202.andie.Draw;

import java.awt.image.*;
import java.awt.*;
import java.awt.geom.*;
import cosc202.andie.ImageOperation;
import cosc202.andie.ImagePanel;


/**
 * <p>
 * ImageOperation to draw oval.
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
     * Constructor. Creates a new DrawOval operation
     * </p>
     *
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
     * Draws Oval on image.
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
