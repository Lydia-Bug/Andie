package cosc202.andie.Draw;

import java.awt.image.*;
import java.awt.*;
import java.awt.geom.*;
import cosc202.andie.ImageOperation;
import cosc202.andie.ImagePanel;


/**
 * <p>
 * ImageOperation to draw horizontal, vertical and diagonal lines. 
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Ella Taylor
 * @version 1.0
 */
public class DrawLine implements ImageOperation, java.io.Serializable   {


    Color c;
    int thickness;
    int lineType;
    ImagePanel target;


    /**
     * Creates DrawLine instance.
     * @param c colour
     * @param thickness brush width
     * @param lineType orientation of line
     * @param target imagepanel 
     */
    public DrawLine(Color c, int thickness, int lineType, ImagePanel target) {
        this.c = c;
        this.thickness = thickness;
        this.lineType = lineType;
        this.target = target;
    }

    /**
    * Draws line according to line type.
    * @param input image to which operation is applied
    * @return input image after line has been drawn. 
    */
    public BufferedImage apply(BufferedImage input) {
        Rectangle2D m = target.GetMouseRectangle();
        Graphics2D g2 = input.createGraphics();
        g2.setStroke(new BasicStroke(thickness));
        g2.setPaint(c);

        if(lineType == 0) g2.drawLine((int) m.getX(), (int) m.getY(), (int) m.getX(), (int) (m.getHeight() + m.getY()));
        if(lineType == 1) g2.drawLine((int) m.getX(), (int) m.getY(), (int) (m.getWidth() + m.getX()), (int) m.getY());
        if(lineType == 2) g2.drawLine((int) m.getX(), (int) m.getY(), (int) (m.getWidth() + m.getX()), (int) (m.getHeight() + m.getY()));
        if(lineType == 3) g2.drawLine((int) m.getX(), (int) (m.getHeight() + m.getY()), (int) (m.getWidth() + m.getX()), (int) m.getY());
        
        g2.dispose();
        return input;
    }
    
}

