package ui;

import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import kmeans.Cluster;
import kmeans.Point;

import java.util.ArrayList;

/**
 * Created by Caevol on 5/9/2017.
 */
public class PointPane extends Pane {
    private ArrayList<Cluster> clusters;

    public PointPane(ArrayList<Cluster> cluster){
        setMinHeight(700);
        setMinWidth(700);
        setHeight(700);
        setWidth(700);
        clusters = cluster;
        placePoints();
    }

    public void setPoints(ArrayList<Cluster> cluster){
        clusters = cluster;
        this.getChildren().clear();
        placePoints();
    }

    public void placeCentroids(ArrayList<Cluster> cluster){
        int colorIter = 0;
        Color col = new Color(0,0,0,0);
        for(Cluster s: cluster){
            col = colorChooser(colorIter);
            Point p = s.getCentroid();
            Circle c = new Circle((p.getX()*this.getWidth()), p.getY()*this.getHeight(), 10, col);
            this.getChildren().add(c);
            colorIter ++;
            colorIter %= 6;
        }
    }

    private void placePoints(){

        int colorIter = 0;
        Color col = new Color(0, 0, 0, 0);
        for(Cluster s: clusters){
            col = colorChooser(colorIter);
            for(Point p: s.getPoints()){

                Rectangle c = new Rectangle((p.getX()*this.getWidth()), (p.getY()*this.getHeight()), 5, 5);
                c.setFill(col);
                this.getChildren().add(c);
            }
            colorIter ++;
            colorIter %= 6;
        }
    }

    public void placeLines(){
        int colorIter = 0;
        Color col = new Color(0,0,0,0);
        for(Cluster s: clusters){
            col = colorChooser(colorIter);
            col = new Color(col.getRed(), col.getGreen(), col.getBlue(), .5);
            Point cent = s.getCentroid();
            for(Point p: s.getPoints()){
                Line l = new Line(p.getX() * getWidth(), p.getY() * getHeight(), cent.getX() * getWidth(), cent.getY() * getHeight());

                l.setStroke(col);
                getChildren().add(l);

            }
            Text t = new Text(cent.getX() * getWidth() - 7, cent.getY() * getHeight() + 5, Integer.toString(s.getPoints().size()));

            getChildren().add(t);
            colorIter ++;
            colorIter %= 6;
        }
    }

    private Color colorChooser(int i){
        switch(i){
            case 0: return Color.RED;
            case 1: return Color.BLUE;
            case 2: return Color.GREEN;
            case 3: return Color.YELLOW;
            case 4: return Color.CYAN;
            case 5: return Color.GOLD;
            default: return Color.GRAY;
        }
        //return new Color(.05*i % 1, .25 + .035*i % 1, .1 + .02*i % 1, 1);

    }


}
