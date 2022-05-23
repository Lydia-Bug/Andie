package cosc202.andie.Transformations;

import java.awt.*;

import java.awt.image.BufferedImage;

import cosc202.andie.ImageOperation;
import cosc202.andie.ImagePanel;

public class ImageCrop implements ImageOperation {
    ImagePanel img = new ImagePanel();
    Shape selectedArea = null;
    BufferedImage croppedImage;
    int x, y, width, height;

    public ImageCrop() {
        x = img.getBounds().x;
        y = img.getBounds().y;
        width = img.getBounds().width;
        height = img.getBounds().height;
    }

    @Override
    public BufferedImage apply(BufferedImage input) {
        // CroppedImage of type BufferedImage that uses a built in method (getSubimage)
        // to produce a new set of the image
        // of specified dimensions ._.

        System.out.println("START: " + img.getStartDrag());

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