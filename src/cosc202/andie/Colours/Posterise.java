package cosc202.andie.Colours;

import java.awt.image.*;

import cosc202.andie.ImageOperation;

import java.util.Random;
import java.util.ArrayList;
import java.lang.Math;
import java.io.*;
import java.util.*;

/**
 * <p>
 * ImageOperation to posterise an image
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class Posterise implements ImageOperation, java.io.Serializable {

    private int layers;
    /**
     * <p>
     * Create a new Posterise operation.
     * </p>
     */
    Posterise(int layers) {
        this.layers = layers;
    }

    /**
     * <p>
     * Posterise an image
     * </p>
     * 
     * <p>
     * 
     * </p>
     * 
     * @param input The image to be posterised
     * @return The resulting posterised image
     */
    public BufferedImage apply(BufferedImage input) {
  
        Random r = new Random();
        int[] centroid1 = getRGBArray(input.getRGB(r.nextInt(input.getWidth()), r.nextInt(input.getHeight())));
        int[] centroid2 = getRGBArray(input.getRGB(r.nextInt(input.getWidth()), r.nextInt(input.getHeight())));
        int[] centroid3 = getRGBArray(input.getRGB(r.nextInt(input.getWidth()), r.nextInt(input.getHeight())));
        int[] centroid4 = getRGBArray(input.getRGB(r.nextInt(input.getWidth()), r.nextInt(input.getHeight())));
        int[] centroid5 = getRGBArray(input.getRGB(r.nextInt(input.getWidth()), r.nextInt(input.getHeight())));


        ArrayList<int[]> cluster1 = new ArrayList<int[]>();
        ArrayList<int[]> cluster2 = new ArrayList<int[]>();
        ArrayList<int[]> cluster3 = new ArrayList<int[]>();
        ArrayList<int[]> cluster4 = new ArrayList<int[]>();
        ArrayList<int[]> cluster5 = new ArrayList<int[]>();

        cluster1.add(centroid1);
        cluster2.add(centroid2);
        cluster3.add(centroid3);
        cluster4.add(centroid4);
        cluster5.add(centroid5);

        //System.out.println(Arrays.toString(centroid1) + " " + Arrays.toString(centroid2) + " " + Arrays.toString(centroid3) + " " + Arrays.toString(centroid4) + " " + Arrays.toString(centroid5));
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int cluster = 1;
                double distance = distanceBetween(cluster1.get(0), getRGBArray(input.getRGB(x,y)));
                if(distanceBetween(cluster2.get(0), getRGBArray(input.getRGB(x,y))) < distance){
                    cluster = 2;
                    distance = distanceBetween(cluster2.get(0), getRGBArray(input.getRGB(x,y)));
                }
                if(distanceBetween(cluster3.get(0), getRGBArray(input.getRGB(x,y))) < distance){
                    cluster = 3;
                    distance = distanceBetween(cluster3.get(0), getRGBArray(input.getRGB(x,y)));
                }
                if(distanceBetween(cluster4.get(0), getRGBArray(input.getRGB(x,y))) < distance){
                    cluster = 4;
                    distance = distanceBetween(cluster4.get(0), getRGBArray(input.getRGB(x,y)));
                }
                if(distanceBetween(cluster5.get(0), getRGBArray(input.getRGB(x,y))) < distance){
                    cluster = 5;
                    distance = distanceBetween(cluster5.get(0), getRGBArray(input.getRGB(x,y)));
                }

                if(cluster == 1){
                    cluster1.add(getRGBArray(input.getRGB(x,y)));
                }
                if(cluster == 2){
                    cluster2.add(getRGBArray(input.getRGB(x,y)));
                }
                if(cluster == 3){
                    cluster3.add(getRGBArray(input.getRGB(x,y)));
                }
                if(cluster == 4){
                    cluster4.add(getRGBArray(input.getRGB(x,y)));
                }
                if(cluster == 5){
                    cluster5.add(getRGBArray(input.getRGB(x,y)));
                }

                
                //argb = (a << 24) | (newR << 16) | (newG << 8) | newB;
                //input.setRGB(x, y, argb);
            }
        }

        
        
        return input;
    }

    private double distanceBetween(int[] a, int[] b){
        return Math.sqrt(Math.pow((a[0]-b[0]),2) + Math.pow((a[1]-b[1]),2) + Math.pow((a[2]-b[2]),2) + Math.pow((a[3]-b[3]),2));
    }

    private int[] getRGBArray(int argb){
        int a = (argb & 0xFF000000) >> 24;
        int r = (argb & 0x00FF0000) >> 16;
        int g = (argb & 0x0000FF00) >> 8;
        int b = (argb & 0x000000FF);

        int[] argbArray= {a,r,g,b};
        return argbArray;
    }
    
}
