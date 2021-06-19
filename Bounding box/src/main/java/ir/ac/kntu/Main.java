package ir.ac.kntu;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        //TODO: Assume that your program starts from this method
        Group root = new Group();
        Scene scene = new Scene(root, 1300, 700, Color.YELLOW);
        List<Point> pointList = new ArrayList<>();
        List<Double> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();

        pointList.add(new Point(150,300));
        xValues.add((double) 150);
        yValues.add((double) 300);
        pointList.add(new Point(90 , 500));
        xValues.add((double) 90);
        yValues.add((double) 500);
        pointList.add(new Point(80 , 56));
        xValues.add((double) 80);
        yValues.add((double) 56);
        pointList.add(new Point(190 , 10));
        xValues.add((double) 190);
        yValues.add((double) 10);
        pointList.add(new Point(100,350));
        xValues.add((double) 100);
        yValues.add((double) 350);
        pointList.add(new Point(590.9,315.7));
        xValues.add((double) 590.9);
        yValues.add((double) 315.7);
        pointList.add(new Point(125 , 550));
        xValues.add((double) 125);
        yValues.add((double) 550);


        Point x = getMinMax(xValues);
        double minX = x.getX();
        double maxX = x.getY();

        System.out.println(minX + " < x < " + maxX);

        Point y = getMinMax(yValues);
        double minY = y.getX();
        double maxY = y.getY();

        System.out.println(minY + " < y < " + maxY);


        for(Point point : pointList){
            root.getChildren().add(new Circle(point.getX() , point.getY() , 3 , Color.RED));
        }

        root.getChildren().add(new Line(minX , minY , maxX , minY));
        root.getChildren().add(new Line(minX , minY , minX , maxY));
        root.getChildren().add(new Line(minX , maxY , maxX , maxY));
        root.getChildren().add(new Line(maxX , minY , maxX , maxY));



        primaryStage.setScene(scene);
        primaryStage.setTitle("Bounding Box 2D");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void init() {
        System.out.println("Initializing...");
    }

    @Override
    public void stop() {
        System.out.println("Closing....");
    }
    public static void main(String[] args) {
        launch(args);
    }

    public static Point getMinMax(List<Double> list){
        int n = list.size();
        List<Double> min = new ArrayList<>();
        List<Double> max = new ArrayList<>();
        if(n % 2 == 1){
            n = n-1 ;
        }
        for(int i = 0 ; i < n ; i +=2 ){
            if(list.get(i) > list.get(i+1)){
                max.add(list.get(i));
                min.add(list.get(i+1));
            }else{
                max.add(list.get(i+1));
                min.add(list.get(i));
            }
        }
        if(list.size() % 2 == 1){
            min.add(list.get(list.size()-1));
            max.add(list.get(list.size()-1));
        }
        Point result = new Point(Collections.min(min) , Collections.max(max));
        return result;
    }

}
class Point{
    private double x;
    private double y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
