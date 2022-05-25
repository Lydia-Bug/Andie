package cosc202.andie.Filters;

import java.awt.image.*;
import java.util.*;
import java.awt.Graphics2D;

import cosc202.andie.ImageOperation;

/**
 * <p>
 * ImageOperation to apply a Mean (simple blur) filter.
 * </p>
 * 
 * <p>
 * A Mean filter blurs an image by replacing each pixel by the average of the
 * pixels in a surrounding neighbourhood, and can be implemented by a convoloution.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Steven Mills
 * @version 1.0
 */
public class MeanFilter implements ImageOperation, java.io.Serializable {
    
    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
     */
    private int radius;
    private int x, y, width, height;
    private boolean selection;

    /**
     * <p>
     * Construct a Mean filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param x,y,width,height,radius The radius of the newly constructed MeanFilter
     */
    MeanFilter(int radius, int x, int y, int width, int height, boolean selection) {
        this.radius = radius;    
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.selection = selection;
    }

    /**
     * <p>
     * Construct a Mean filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Mean filter has radius 1.
     * </p>
     * 
     * @see MeanFilter(int)
     */
    MeanFilter() {
        radius = 1;
    }

    /**
     * <p>
     * Apply a Mean filter to an image.
     * </p>
     * 
     * <p>
     * As with many filters, the Mean filter is implemented via convolution.
     * The size of the convolution kernel is specified by the {@link radius}.  
     * Larger radii lead to stronger blurring.
     * </p>
     * 
     * @param input The image to apply the Mean filter to.
     * @return The resulting (blurred)) image.
     */
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
        
        int size = (2*radius+1) * (2*radius+1);
        float [] filterArray = new float[size];
        Arrays.fill(filterArray, 1.0f/size);

        if(!selection){
            Kernel kernel = new Kernel(2*radius+1, 2*radius+1, filterArray);
            System.out.println(kernel.getXOrigin());
            ConvolveOp convOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
            BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
            convOp.filter(input, output);

            BufferedImage paddedinput = paddedImage(input, radius);
            BufferedImage blurredPaddedImage = operatedImage(paddedinput, convOp);
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
                    for(int i = 0; i < filterArray.length; i++){
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
                        newA += a*filterArray[i];
                        newR += r*filterArray[i];
                        newG += g*filterArray[i];
                        newB += b*filterArray[i];
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
