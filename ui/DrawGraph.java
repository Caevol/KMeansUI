package ui;/**
 * Created by Caevol on 5/9/2017.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kmeans.Cluster;
import kmeans.KMeans;
import java.util.ArrayList;

public class DrawGraph extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private BorderPane mainPane;
    private PointPane graphPane;
    private KMeans kmeans;





    @Override
    public void start(Stage primaryStage) {
        kmeans = new KMeans(75, 5);
        kmeans.init();
        kmeans.calculate();

        //create top bar
        Button topButton = new Button("Create new Graph");
        Text pc = new Text("Points:");
        Text cc = new Text("Clusters:");
        TextField pointCount = new TextField();
        TextField clusterCount = new TextField();
        HBox topBar = new HBox();
        topBar.getChildren().addAll(pc,pointCount,cc,clusterCount,topButton);
        topButton.setOnMouseClicked(e -> {
            createNewGraph(Integer.parseInt(pointCount.textProperty().get()), Integer.parseInt(clusterCount.textProperty().get()));
        });
        //topButton.setOnMouseClicked(e -> createNewGraph(75, 5));
        //end top bar, should have been done in a separate class


        mainPane = new BorderPane();
        graphPane = new PointPane(kmeans.getClusters());
        graphPane.placeCentroids(kmeans.getClusters());

        mainPane.setCenter(graphPane);
        mainPane.setTop(topBar);
        Scene scene = new Scene(mainPane, 750, 750);
        primaryStage.setScene(scene);
        primaryStage.setTitle("K-means clustering");
        primaryStage.show();

    }


    public void createNewGraph(int points, int clusters){
        kmeans.getNewClusters(points, clusters);
        graphPane.setPoints(kmeans.getClusters());
        graphPane.placeCentroids(kmeans.getClusters());
    }






}
