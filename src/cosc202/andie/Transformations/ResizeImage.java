//Skeleton class for resizing image - documentation to be added

package cosc202.andie.Transformations;

import java.awt.*;
import java.awt.image.*;
import java.lang.annotation.Target;
import java.io.*;
import javax.imageio.*;

import cosc202.andie.ImageOperation;

public class ResizeImage implements ImageOperation, java.io.Serializable {
    private double scaleFactor;

    public ResizeImage(double scaleFactor) {
        this.scaleFactor = scaleFactor;
        scaleFactor = scaleFactor / 1;
        // this.newWidth = newWidth;
    }

    ResizeImage() {
    }

    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();

        int newHeight = (int) (height * scaleFactor);
        int newWidth = (int) (width * scaleFactor);

        Image img = input.getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING);
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, input.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(img, 0, 0, null);

        return resizedImage;
    }

}
