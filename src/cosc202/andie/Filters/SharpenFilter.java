package cosc202.andie.Filters;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import cosc202.andie.ImageOperation;

/**
 * Class to apply a sharpening filter
 * 
 * A sharpening filter increases the contrast of each pixel compared to those around it
 * 
 * @author Callum Walker
 */

public class SharpenFilter implements ImageOperation, java.io.Serializable{
    
    private int sharpness;
    private int x, y, width, height;
    private boolean selection;

    /**
     * Constructor for the class
     * 
     * @param x,y,width,height,sharpness The degree of sharpening
     * 
     */
    SharpenFilter(int sharpness, int x, int y, int width, int height, boolean selection) {
        this.sharpness = sharpness;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.selection = selection;
    }

    /**
     * Default contructor.
     * 
     * Sets the sharpness to 1 which will apply some sharpening
     * 
     */
    SharpenFilter() {
        sharpness = 1;
    }

    /**
     * Apply the sharpness filter to the image
     * 
     * This is done using a ConvolveOp class.
     * I have elected not to allow the user to adjust the size of
     * the kernal, instead the strength of the sharpness. These
     * values will likely change as it currently looks kinda bad
     * 
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
        

        float[] filterArray = {0, -sharpness, 0,
                               -sharpness, (4*sharpness)+1, -sharpness,
                               0, -sharpness, 0}; //This array defines the operation being down on each kernal. Total must be 1

        //calculates values image
        int[][] image = new int[input.getWidth()][input.getHeight()];
        for (int y = this.y; y < this.height+this.y; ++y) {
            for (int x = this.x; x < this.width+this.x; ++x) {
                double newA = 0;
                double newR = 0;
                double newG = 0;
                double newB = 0;

                int kernelX = -1;
                int kernelY = -1;
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
                    if(kernelX > 1){
                        kernelX = -1;
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
