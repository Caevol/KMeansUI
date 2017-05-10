# KMeansUI
Basic UI that generates data and clusters it using KMeans algorithm<p>
Created by Logan Pedersen<p>

Uses the KMeans algorithm provided by DataOnFocus, www.dataonfocus.com, 2015<p>

Simple UI that allows you to generate data and run K-means clustering on it graphically.<p>
This algorithm uses the iterative approach, which randomly generates a centroid for each cluster. Then attaches points to the cluster
and moves the centroid to the center of the cluster repeatedly until the centroid does not move.<p>
This is a relatively fast algorithm, that can safely cluster data as large as 100,000 in seconds (although at that point 
it becomes a colorful blob rather than a collection of datapoints).<p>
Each iteration of the algorithm runs at (#ofPoints * #ofClusters) speed. So as the number of points rises, for fast calculations, keep the number of clusters low.<p>
