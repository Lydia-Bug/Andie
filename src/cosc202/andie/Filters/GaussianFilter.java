package cosc202.andie.Filters;

import java.awt.image.BufferedImage;
import cosc202.andie.ImageOperation;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.Graphics2D;

/**
 * A class to apply a gaussian filter to an image
 * 
 * @author Callum Walker
 * 
 */
public class GaussianFilter implements ImageOperation, java.io.Serializable{
    
    private int radius;
    
    /**
     * Constructor for the GaussianFilter
     * 
     * @param radius Size of the kernel to apply the filter
     */
    GaussianFilter(int radius) {
        this.radius = radius;
    }

    /**
     * Default constructor incase no input is given
     * 
     * 3 is the minimum value that makes sense
     * 
     */
    GaussianFilter() {
        radius = 3;
    }

    public BufferedImage apply(BufferedImage input) {

        int rows = radius * 2 + 1;
        float[] kernelData = new float[rows*rows];

        double sigma = radius / 3;
        double sigma22 = 2*sigma*sigma;
        double sqrtPiSigma = Math.sqrt(Math.PI * sigma22);
        double radius2 = radius * radius;

        double total = 0;
        int index = 0;
        double distance2;

        for(int y = -radius; y <= radius; y++) {
            for(int x = -radius; x <= radius; x++) {
                distance2 = 1.0 * x * x + 1.0 * y * y;
                if (distance2 > radius2) {
                    kernelData[index] = 0;
                } else {
                    kernelData[index] = (float) (Math.exp(-distance2 / sigma22) / sqrtPiSigma);
                }
                total += kernelData[index];
                ++index;
            }
        }

        for (index = 0; index < kernelData.length; index++) {
            kernelData[index] /= total;
        }

        BufferedImage paddedinput = paddedImage(input, radius);
        BufferedImage blurredPaddedImage = operatedImage(paddedinput, new ConvolveOp(new Kernel(rows, rows, kernelData), ConvolveOp.EDGE_ZERO_FILL, null));
        return blurredPaddedImage.getSubimage(radius, radius, input.getWidth(), input.getHeight());
    }

    public static BufferedImage paddedImage(BufferedImage input, int padding) {
        if (padding == 0) {
            return input;
        }

        BufferedImage newImage = newArgbBufferedImage(input.getWidth() + padding * 2,
                input.getHeight() + padding * 2);
        Graphics2D g = (Graphics2D) newImage.getGraphics();
        g.drawImage(input, padding, padding, null);
        return newImage;
    }

    public static BufferedImage operatedImage(BufferedImage input, BufferedImageOp op) {
        BufferedImage newImage = newArgbBufferedImage(input.getWidth(), input.getHeight());
        Graphics2D g = (Graphics2D) newImage.getGraphics();
        g.drawImage(input, op, 0, 0);
        return newImage;
    }

    public static BufferedImage newArgbBufferedImage(int width, int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

}
