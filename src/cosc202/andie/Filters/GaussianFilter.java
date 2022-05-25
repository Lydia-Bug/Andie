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
    private int x, y, width, height;
    private boolean selection;
    
    /**
     * Constructor for the GaussianFilter
     * 
     * @param x,y,width,height,radius Size of the kernel to apply the filter
     */
    GaussianFilter(int radius, int x, int y, int width, int height, boolean selection) {
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.selection = selection;
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
        if(selection){
            if(this.x > input.getWidth() || this.y > input.getHeight()){
                return input;
            }
            if (this.x + this.width > input.getWidth()) {
                width = input.getWidth() - x;
            }
            if (this.y + this.height > input.getHeight()) {
                height = input.getHeight() - y;
            }
            if (this.x < 0) {
                this.x = 0;
            }
            if (this.y < 0) {
                this.y = 0;
            }
        }else{
            this.x = 0;
            this.y = 0;
            this.width = input.getWidth();
            this.height = input.getHeight();
        }
        
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
        if(!selection){
            BufferedImage paddedinput = paddedImage(input, radius);
            BufferedImage blurredPaddedImage = operatedImage(paddedinput, new ConvolveOp(new Kernel(rows, rows, kernelData), ConvolveOp.EDGE_ZERO_FILL, null));
            return blurredPaddedImage.getSubimage(radius, radius, input.getWidth(), input.getHeight());
        }else{
           //calculates values image
           int[][] image = new int[input.getWidth()][input.getHeight()];
           for (int y = this.y; y < this.height+this.y; ++y) {
               for (int x = this.x; x < this.width+this.x; ++x) {
                   double newA = 0;
                   double newR = 0;
                   double newG = 0;
                   double newB = 0;

                   int kernelX = -radius;
                   int kernelY = -radius;
                   int kx = kernelX;
                   int ky = kernelY;
                   for(int i = 0; i < kernelData.length; i++){
                       if(x + kernelX < 0 || x + kernelX >= input.getWidth()){
                           kx = 0;
                       }
                       if(y + kernelY < 0 || y + kernelY >= input.getHeight()){
                           ky = 0;
                       }
                       int argb = input.getRGB(x+kx, y+ky);
                       int a = (argb & 0xFF000000) >> 24;
                       int r = (argb & 0x00FF0000) >> 16;
                       int g = (argb & 0x0000FF00) >> 8;
                       int b = (argb & 0x000000FF);
                       newA += a*kernelData[i];
                       newR += r*kernelData[i];
                       newG += g*kernelData[i];
                       newB += b*kernelData[i];
                       kernelX ++;
                       if(kernelX > radius){
                           kernelX = -radius;
                           kernelY++;
                       }
                       kx = kernelX;
                       ky = kernelY;
                   }
                   if(newR < 0){
                       newR = 0;
                   }else if(newR > 255){
                       newR = 255;
                   }
                   if(newG < 0){
                       newG = 0;
                   }else if(newG > 255){
                       newG = 255;
                   }
                   if(newB < 0){
                       newB = 0;
                   }else if(newB > 255){
                       newB = 255;
                   }
                   image[x][y] = ((int)newA << 24) | ((int)newR << 16) | ((int)newG << 8) | (int)newB;
                   
               }
           }

           //applys new values to images
           for (int y = 0; y < input.getHeight(); ++y) {
               for (int x = 0; x < input.getWidth(); ++x) {
                   if(image[x][y] != 0){
                       input.setRGB(x, y, image[x][y]);
                   }
               }
           }
           return input; 
        }
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
