package cosc202.andie.Transformations;

import java.awt.*;

import java.awt.image.BufferedImage;

import cosc202.andie.ImageOperation;
import cosc202.andie.ImagePanel;

public class ImageCrop extends ImagePanel implements ImageOperation {
    ImagePanel img = new ImagePanel();
    Shape selectedArea = null;
    BufferedImage croppedImage;

    public ImageCrop() {

    }

    @Override
    public BufferedImage apply(BufferedImage input) {
        // CroppedImage of type BufferedImage that uses a built in method (getSubimage)
        // to produce a new set of the image
        // of specified dimensions ._.
        croppedImage = input.getSubimage(400, 400, 100,
                100);
        // croppedImage = input.getSubimage(img.getBounds().x, img.getBounds().y,
        // img.getBounds().width,
        // img.getBounds().height);

        Graphics2D g = croppedImage.createGraphics();

        g.drawImage(croppedImage, 0, 0, null);
        g.dispose();
        return croppedImage;
    }
}