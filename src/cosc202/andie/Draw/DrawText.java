package cosc202.andie.Draw;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

import javax.swing.JTextField;

import cosc202.andie.ImageOperation;

/**
 * <p>
 * DrawText class.
 * </p>
 * 
 * <p>
 * This class takes the bounds of the mouse selection from ImagePanel
 * to draw text on top of the image within those dimensions of the selection
 * bounds.
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Hamzah Alansi
 * @version 1.0
 */

public class DrawText implements ImageOperation, java.io.Serializable {
    int x, y, width, height;
    String txt = "help lol";

    /**
     * <p>
     * Constructor. Creates a new DrawText operation.
     * </p>
     * 
     * @param x,y,width,height
     */
    public DrawText(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        // this.txt = txt;
    }

    /**
     * <p>
     * Uses the Built-in draw operations within the Graphics2D class to create
     * a Text on the Image with certain dimensions decided by mouse selection.
     *
     * </p>
     * 
     * @param input The image to be drawn over.
     * @return input with Text.
     */
    @Override
    public BufferedImage apply(BufferedImage input) {

        Graphics2D g = input.createGraphics();
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.setColor(Color.BLACK);
        FontMetrics fm = g.getFontMetrics();

        int stringWidth = fm.stringWidth(txt);
        int startX = x + ((width - stringWidth) / 2);
        int startY = y + ((height + fm.getHeight()) / 2);

        g.drawString(txt, startX, startY);
        g.setColor(Color.BLACK);
        g.dispose();
        return input;
    }
}
