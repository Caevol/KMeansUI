/* 
 * KMeans.java ; Cluster.java ; Point.java
 *
 * Solution implemented by DataOnFocus
 * www.dataonfocus.com
 * 2015
 *
*/
package kmeans;

import java.util.ArrayList;
import java.util.List;


public class KMeans{

    //Number of Clusters. This metric should be related to the number of points
    private int NUM_CLUSTERS = 3;
    //Number of Points
    private int NUM_POINTS = 15;
    //Min and Max X and Y
    private static final int MIN_COORDINATE = 0;
    private static final int MAX_COORDINATE = 1;

    private List<Point> points;
    private ArrayList<Cluster> clusters;

    public KMeans() {
        this.points = new ArrayList<Point>();
        this.clusters = new ArrayList<Cluster>();
    }

    public KMeans(int points, int clusters) {
        this();
        NUM_CLUSTERS = clusters;
        NUM_POINTS = points;
    }

    public void getNewClusters(int points, int clusters){
        this.clusters.clear();
        this.points.clear();
        NUM_CLUSTERS = clusters;
        NUM_POINTS = points;
        init();
        calculate();
    }

    //Initializes the process
    public void init() {
        //Create Points
        points = Point.createRandomPoints(MIN_COORDINATE,MAX_COORDINATE,NUM_POINTS);

        //Create Clusters
        //Set Random Centroids
        for (int i = 0; i < NUM_CLUSTERS; i++) {
            Cluster cluster = new Cluster(i);
            Point centroid = Point.createRandomPoint(MIN_COORDINATE,MAX_COORDINATE);
            cluster.setCentroid(centroid);
            clusters.add(cluster);
        }

        //Print Initial state
        //plotClusters();
    }



    private void plotClusters() {
        for (int i = 0; i < NUM_CLUSTERS; i++) {
            Cluster c = (Cluster) clusters.get(i);
            c.plotCluster();
        }
    }

    private double getAccuracyScore(){
        //return clusters.size()*clusters.size() +
        //return sum of mean distances
        double score = 0.0;
        for(Cluster c: clusters){
            double sum = 0.0;
            for(Point p: c.getPoints()){
                sum += Point.distance(p, c.getCentroid());
            }
            //System.out.println("Sum: " + (sum/c.getPoints().size()));
            if(c.getPoints().size() != 0) {
                score += sum / c.getPoints().size();
            }
        }
        return score;
    }

    //The process to calculate the K Means, with iterating method.
    //The iterating method works by beginning with random centroid locations, then finding clusters and moving the centroids to the center of those clusters
    //The clusters are then recalculated, and the centroids are moved to the center of the new clusters repeatedly until the centroids are not moved

    public void calculate() {
        boolean finish = false;
        int iteration = 0;

        // Add in new data, one at a time, recalculating centroids with each new one. 
        while(!finish) {
            //Clear cluster state
            clearClusters();

            List<Point> lastCentroids = getCentroids();

            //Assign points to the closer cluster
            assignCluster();

            //Calculate new centroids.
            calculateCentroids();

            iteration++;

            List<Point> currentCentroids = getCentroids();

            //Calculates total distance between new and old Centroids
            double distance = 0;
            for(int i = 0; i < lastCentroids.size(); i++) {
                distance += Point.distance(lastCentroids.get(i),currentCentroids.get(i));
            }
            //System.out.println("#################");
            //System.out.println("Iteration: " + iteration);
            //System.out.println("Centroid distances: " + distance);
            //plotClusters();

            if(distance == 0) {
                finish = true;
            }
        }

        //System.out.println(getAccuracyScore());
        //System.out.println("Iteration: " + iteration);
    }

    public ArrayList<Cluster> getClusters(){
        return clusters;
    }

    private void clearClusters() {
        for(Cluster cluster : clusters) {
            cluster.clear();
        }
    }

    public ArrayList<Point> getCentroids() {

        ArrayList<Point> centroids = new ArrayList(NUM_CLUSTERS);
        for(Cluster cluster : clusters) {
            Point aux = cluster.getCentroid();
            Point point = new Point(aux.getX(),aux.getY());
            centroids.add(point);
        }
        return centroids;
    }

    private void assignCluster() {
        double max = Double.MAX_VALUE;
        double min = max;
        int cluster = 0;
        double distance = 0.0;

        /**
         * for every point, find the cluster with the smallest distance to that point and assign the point to that cluster
         */
        for(Point point : points) {
            min = max;
            for(int i = 0; i < NUM_CLUSTERS; i++) {
                Cluster c = clusters.get(i);
                distance = Point.distance(point, c.getCentroid());
                if(distance < min){
                    min = distance;
                    cluster = i;
                }
            }
            point.setCluster(cluster);
            clusters.get(cluster).addPoint(point);
        }
    }

    private void calculateCentroids() {
        /**
         * for every cluster
         * move the centroid to the mean of the clusters points
         */
        for(Cluster cluster : clusters) {
            double sumX = 0;
            double sumY = 0;
            List<Point> list = cluster.getPoints();
            int n_points = list.size();

            for(Point point : list) {
                sumX += point.getX();
                sumY += point.getY();
            }

            Point centroid = cluster.getCentroid();
            if(n_points > 0) {
                double newX = sumX / n_points;
                double newY = sumY / n_points;
                centroid.setX(newX);
                centroid.setY(newY);
            }
        }
    }
}