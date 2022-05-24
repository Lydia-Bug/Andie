package cosc202.andie.Filters;

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
        //Creates an array of clusters
        ArrayList<int[]>[] clusters = createClusters(input, layers);
        //Creates arraylist of previous means so I can check when the means converge
        int[][] previousMeans = new int[clusters.length][];
        boolean notConverged = true;
        while(notConverged){
            //records previous means, so figure out when clusters converge
            for(int p = 0; p < previousMeans.length; p++){
                previousMeans[p] = clusters[p].get(0);
            }
            //Finds and puts mean at start of cluster array
            clusters = findMeans(clusters);
            //Sorts datapoints in clusters
            clusters = sortClusters(clusters);
            //Checks whether cluster has converged
            notConverged = false;
            for(int p = 0; p < previousMeans.length; p++){
                if(previousMeans[p] != clusters[p].get(0)){
                    notConverged = true;
                }
            }
            
        }
        //Goes over every pixel and changes it to whatever mean colour its closests to
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

    /**
     * <p>
     * Creates and initilzes the array of clusters, and puts data points from the image 
     * into the clusters
     * </p>
     * 
     * <p>
     * 
     * </p>
     * 
     * @param input The image for the data points to be put in clusters
     * @param layers The amount of layers, or colours, which will be the amount of clusters
     * @return The array of all the clusters
     */
    private ArrayList<int[]>[] createClusters(BufferedImage input, int layers){
        //Initializes an array of clusters
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
        /* The xstep and ystep make it so for large images it doesn't sort through every
        * single pixel. Because when it was doing this it would take way too long
        */
        int yStep = (int)Math.ceil((double)input.getHeight()/200);
        int xStep = (int)Math.ceil((double)input.getWidth()/200);
        for (int y = 0; y < input.getHeight(); y=y+yStep) {
            for (int x = 0; x < input.getWidth(); x=x+xStep) { 
                clusters[cluster].add(getRGBArray(input.getRGB(x,y)));
                n++;
                if(((Math.ceil((double)input.getHeight()/yStep))*(Math.ceil((double)input.getWidth()/xStep)))/layers< n){
                    cluster++;
                    n = 0;
                }
            }
        }
        return clusters;
    }

    /**
     * <p>
     * Finds the mean data point in each cluster (also known as the centroid), and brings 
     * it to the front of the arraylist representing the cluster
     * </p>
     * 
     * @param clusters The array of clusters pre having their means found
     * @return The array clusters, with their means at the front
     */
    private ArrayList<int[]>[] findMeans(ArrayList<int[]>[] clusters){    
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
        return clusters;
    }

    /**
     * <p>
     * Sorts through all the clusters so that each data point is in the cluster with its
     * closests centroid
     * </p>
     * 
     * @param clusters The array of clusters pre sorting
     * @return The array clusters, after sorting
     */
    private ArrayList<int[]>[] sortClusters(ArrayList<int[]>[] clusters){
        /* Goes through the clusters and puts the points in clusters with their closest 
        * centroid. 
        */

        /* This keeps records the initial cluster size, so that the code will only go over
        *   the initial data points in the cluster, rather then the ones that have been 
        * added on from previous clusters
        */
        int[] initialClusterSize = new int[clusters.length];
        for(int s = 0; s < initialClusterSize.length; s++){
            initialClusterSize[s] = clusters[s].size();
        }

        for(int c = 0; c < clusters.length; c++){  
            /*I use the difference, because just using the initialsize doesn't work
            * as the cluster gets smaller, and you end up with an out of bounds error
            */
            int differenceClusterSize = clusters[c].size() - initialClusterSize[c];
            for(int i = 0; i < clusters[c].size() - differenceClusterSize; i++){
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
        return clusters;
    }

    /**
     * <p>
     * Finds the distance between two RGB values by represting them in 3D space
     * </p>
     * 
     * @param a The RGB value
     * @param b The second RGB value
     * @return The distance between the two points
     */
    private double distanceBetween(int[] a, int[] b){
        return Math.sqrt(Math.pow((a[0]-b[0]),2) + Math.pow((a[1]-b[1]),2) + Math.pow((a[2]-b[2]),2) + Math.pow((a[3]-b[3]),2));
    }

    /**
     * <p>
     * Converts the sinuglar int argb value, into an array with red, green, blue, and alpha
     * chanels sperated, making it easier to work with
     * </p>
     * 
     * @param argb The RGB value as a single int
     * @return The RGB value as an array
     */
    private int[] getRGBArray(int argb){
        int a = (argb & 0xFF000000) >> 24;
        int r = (argb & 0x00FF0000) >> 16;
        int g = (argb & 0x0000FF00) >> 8;
        int b = (argb & 0x000000FF);

        int[] argbArray= {a,r,g,b};
        return argbArray;
    }
    
}
