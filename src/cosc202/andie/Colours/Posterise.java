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
        //Creates and array of clusters
        ArrayList<int[]>[] clusters = new ArrayList[layers];
        for(int i = 0; i < clusters.length; i++){
            clusters[i] = new ArrayList<int[]>();
        }
        /* The clusters are initialized by puting the fist layers'th amount into the first 
        * cluster, then the next layers'th amount into the next cluster and so on.
        * Theres no sorting at this stage, this is just to initalize the arrays
        * and the first data point in each cluster will be the first centroid
        */ 
        int cluster = 0;
        int n = 0;
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                clusters[cluster].add(getRGBArray(input.getRGB(x,y)));
                n++;
                if((input.getHeight()*input.getWidth())/layers < n){
                    cluster++;
                    n = 0;
                }
                //argb = (a << 24) | (newR << 16) | (newG << 8) | newB;
                //input.setRGB(x, y, argb);
            }
        }
        
        for(int runs = 0; runs < 10; runs++){
            
            //Find the mean of each cluster and place it at front of cluster array
            for(ArrayList<int[]> cs : clusters){
                int[] meanRGBA = {0,0,0,0};
                for(int[] c : cs){
                    meanRGBA[0] = meanRGBA[0] + c[0];
                    meanRGBA[1] = meanRGBA[1] + c[1];
                    meanRGBA[2] = meanRGBA[2] + c[2];
                    meanRGBA[3] = meanRGBA[3] + c[3];
                }
                meanRGBA[0] = meanRGBA[0]/cs.size();
                meanRGBA[1] = meanRGBA[1]/cs.size();
                meanRGBA[2] = meanRGBA[2]/cs.size();
                meanRGBA[3] = meanRGBA[3]/cs.size();
                /* The above code finds the mean, but we want to find which existing data point
                * is the mean, so we need to find which data point in the cluster is closest
                * to the mean
                */
                int centroidIndex = 0;
                double smallestDistance = distanceBetween(cs.get(0), meanRGBA);
                for(int i = 0; i < cs.size(); i++){
                    if(distanceBetween(cs.get(i), meanRGBA) < smallestDistance){
                        smallestDistance = distanceBetween(cs.get(i), meanRGBA);
                        centroidIndex = i;
                    }
                }
                //Move mean data point to front of cluster
                int[] centroid = cs.get(centroidIndex);
                cs.remove(centroidIndex);
                cs.add(0, centroid);
            }

            /* Goes through the clusters and points the points in clusters with their closest 
            * centroid. This code will re-go over data points that were placed from one cluster
            * to another, yet to have been goon over, so not as efficeint as it could be
            * but should be fine
            */
            for(int c = 0; c < clusters.length; c++){  
                for(int i = 0; i < clusters[c].size(); i++){
                    //find the clostest centroid
                    int clostestCentroidIndex = 0;
                    double smallestDistance = distanceBetween(clusters[c].get(i), clusters[0].get(0));
                    for(int j = 0; j < clusters.length; j++){
                        if(distanceBetween(clusters[c].get(i), clusters[j].get(0)) < smallestDistance){
                            smallestDistance = distanceBetween(clusters[c].get(i), clusters[j].get(0));
                            clostestCentroidIndex = j;
                        }
                    }
                    //place data point in cluster with closted centroid
                    if(clostestCentroidIndex == c){
                        //do nothing already in correct cluster
                    }else{
                        clusters[clostestCentroidIndex].add(clusters[c].get(i));
                        clusters[c].remove(i);
                    }

                }
            }
            
    }
    for (int y = 0; y < input.getHeight(); ++y) {
        for (int x = 0; x < input.getWidth(); ++x) {
            //find the clostest centroid
            int clostestCentroidIndex = 0;
            double smallestDistance = distanceBetween(getRGBArray(input.getRGB(x,y)), clusters[0].get(0));
            for(int i = 0; i < clusters.length; i++){
                    if(distanceBetween(getRGBArray(input.getRGB(x,y)), clusters[i].get(0)) < smallestDistance){
                        smallestDistance = distanceBetween(getRGBArray(input.getRGB(x,y)), clusters[i].get(0));
                        clostestCentroidIndex = i;
                    }
                }
            int [] centroid = clusters[clostestCentroidIndex].get(0);
            int argb = (centroid[0] << 24) | (centroid[1] << 16) | (centroid[2] << 8) | centroid[3];
            input.setRGB(x, y, argb);
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
