package cosc202.andie.Transformations;

import java.awt.*;

import java.awt.image.BufferedImage;

import cosc202.andie.ImageOperation;
import cosc202.andie.ImagePanel;

public class ImageCrop implements ImageOperation {
    // ImagePanel img = new ImagePanel();
    Shape selectedArea = null;
    BufferedImage croppedImage;
    int x, y, width, height;

    public ImageCrop(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public BufferedImage apply(BufferedImage input) {
        // CroppedImage of type BufferedImage that uses a built in method (getSubimage)
        // to produce a new set of the image
        // of specified dimensions ._
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
        croppedImage = input.getSubimage(x, y, width,
                height);
        // croppedImage = input.getSubimage(img.getBounds().x, img.getBounds().y,
        // img.getBounds().width,
        // img.getBounds().height);

        Graphics2D g = croppedImage.createGraphics();

        g.drawImage(croppedImage, 0, 0, null);
        g.dispose();
        return croppedImage;
    }
}