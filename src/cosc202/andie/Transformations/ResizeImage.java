//Skeleton class for resizing image - documentation to be added

package cosc202.andie.Transformations;

import java.awt.*;
import java.awt.image.*;

import cosc202.andie.ImageOperation;

public class ResizeImage implements ImageOperation, java.io.Serializable {
    private double scaleFactor;

    public ResizeImage(double scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    ResizeImage() {
    }

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

        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, input.getType());
        Graphics2D g = resizedImage.createGraphics();

        g.drawImage(img, 0, 0, null);
        g.dispose();

        return resizedImage;
    }

}
