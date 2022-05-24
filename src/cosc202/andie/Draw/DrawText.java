package cosc202.andie.Draw;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import cosc202.andie.ImageOperation;

public class DrawText implements ImageOperation, java.io.Serializable   {
    int x, y, width, height;

    public DrawText(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public BufferedImage apply(BufferedImage input) {
        int w = input.getWidth() / 3;
        int h = input.getHeight() / 3;
        BufferedImage img = new BufferedImage(
                w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(input, 0, 0, w, h, (ImageObserver) this);
        g2d.setPaint(Color.red);
        g2d.setFont(new Font("Serif", Font.BOLD, 20));
        String s = "Hello, world!";
        FontMetrics fm = g2d.getFontMetrics();
        int x = width - fm.stringWidth(s) - 5;
        int y = fm.getHeight();
        g2d.drawString(s, x, y);
        g2d.dispose();
        return img;
    }
}
