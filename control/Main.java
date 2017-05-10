package control;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.DrawGraph;

/**
 * Created by Caevol on 5/9/2017.
 */
public class Main extends Application{


    public static void main(String args[]){
        DrawGraph g = new DrawGraph();
        g.launch(DrawGraph.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
