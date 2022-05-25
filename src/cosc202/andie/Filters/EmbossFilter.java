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
    
    private int angle;
    private int x, y, width, height;
    private boolean selection;

    /**
     * Constructor for the class
     * 
     * @param x,y,width,height,angle The angle the emboss filter will be applied
     * 
     */
    EmbossFilter(int angle, int x, int y, int width, int height, boolean selection) {
        this.angle = angle;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.selection = selection;
    }

    /**
     * Default contructor.
     * 
     * Sets the angle to 0
     * 
     */
    EmbossFilter() {
        angle = 0;
    }

    /**
     * Apply the emboss filter to the image
     * 
     * I've created by own code to apply the kernal, so that I can deal with negitive values
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

        float[] filterArray = new float[9];

        if(angle < 22){//0
            filterArray[5] = 1;
            filterArray[3] = -1;
        }else if(angle >= 22 && angle < 67){//45
            filterArray[2] = 1;
            filterArray[6] = -1;
        }else if(angle >= 67 && angle < 112){//90
            filterArray[1] = 1;
            filterArray[7] = -1;
        }else if(angle >= 112 && angle < 157){//135
            filterArray[0] = 1;
            filterArray[8] = -1;
        }else if(angle >= 157 && angle < 202){//180
            filterArray[3] = 1;
            filterArray[5] = -1;
        }else if(angle >= 202 && angle < 247){//225
            filterArray[6] = 1;
            filterArray[2] = -1;
        }else if(angle >= 247 && angle < 292){//270
            filterArray[7] = 1;
            filterArray[1] = -1;
        }else if(angle >= 292 && angle < 337){//315
            filterArray[8] = 1;
            filterArray[0] = -1;
        }else{//360
            filterArray[5] = 1;
            filterArray[3] = -1;
        }
         

        //calculates values for image
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
