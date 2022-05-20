package cosc202.andie.Filters;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import cosc202.andie.ImageOperation;

/**
 * Class to apply a emboss filter
 * 
 * An emboss filter reate the effect of theimage being pressed into or raised out 
 * of a sheet of paper (hence the name)
 * 
 * @author Lydia
 */

public class EmbossFilter implements ImageOperation, java.io.Serializable{
    
    private int sharpness;

    /**
     * Constructor for the class
     * 
     * @param sharpness The degree of sharpening
     * 
     */
    EmbossFilter(int sharpness) {
        this.sharpness = sharpness;
    }

    /**
     * Default contructor.
     * 
     * Sets the sharpness to 1 which will apply some sharpening
     * 
     */
    EmbossFilter() {
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

        float[] filterArray = {sharpness, 0, 0,
                               0, 0, 0,
                               0, 0, -sharpness}; //This array defines the operation being down on each kernal. Total must be 1

        //calculates values for border
        int[][] border = new int[input.getWidth()][input.getHeight()];
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
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
                newR = newR+127; 
                newG = newG+127; 
                newB = newB+127; 
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
                border[x][y] = ((int)newA << 24) | ((int)newR << 16) | ((int)newG << 8) | (int)newB;
                
            }
        }

        //applys new values to images
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                if(border[x][y] != 0){
                    input.setRGB(x, y, border[x][y]);
                }
            }
        }


        return input;
    }
}
