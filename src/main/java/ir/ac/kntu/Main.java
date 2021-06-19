package ir.ac.kntu;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root, 1300, 700, Color.BLACK);
        Point[] allPoints = initialValue();

        Point[] xArr = Arrays.copyOf(allPoints, allPoints.length);
        Point[] yArr = Arrays.copyOf(allPoints, allPoints.length);

        Arrays.sort(xArr , (p1,p2) -> p1.getX()-p2.getX());
        Arrays.sort(yArr , (p1,p2) -> p1.getY()-p2.getY());

        System.out.println("The minimum distance is : " + divideAndConquer(xArr, yArr, allPoints.length));

//        Box box = new Box();
//        box.setWidth(200.0);
//        box.setHeight(400.0);
//        box.setDepth(200.0);
//        box.setCullFace(CullFace.FRONT);
//        root.getChildren().add(box);
//        for(Point point : allPoints){
////            root.getChildren().add(new Circle(point.getX() , point.getY() , 3 , Color.RED));
//            root.getChildren().add(new Point3D(point.getX() , point.getY(), point.getZ()));
//        }
//
//        root.getChildren().add(new Line(minX , minY , maxX , minY));
//        root.getChildren().add(new Line(minX , minY , minX , maxY));
//        root.getChildren().add(new Line(minX , maxY , maxX , maxY));
//        root.getChildren().add(new Line(maxX , minY , maxX , maxY));
//
//
//
        primaryStage.setScene(scene);
        primaryStage.setTitle("Let's find minimum distance!");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args){
        launch(args);
    }

    public static Point[] initialValue(){
        Point[] array = { new Point(500,800,200), new Point(0,0,0),new Point(10,90,100),
                new Point(179,990,10), new Point(0,0,0) };

        return array;
    }


    public static double closest(Point p[], int n ){
        double closestDist = p[0].distance(p[1]); // Give a very big number to find minimum distances.
        for (int x = 0; x < n; x++){
            for (int y = x + 1; y < n; y++){
                if (p[x].distance(p[y]) < closestDist)
                    closestDist = p[x].distance(p[y]);
            }
        }
        return closestDist;
    }

    public static double divideAndConquer (Point px[], Point py[], int size){
        if (size <= 3)
            return closest(px, size);
        int intermediate = size / 2;
        Point midPoint = px[intermediate];

        Point[] leftpart = new Point[intermediate + 1];
        Point[] rightpart = new Point[size - intermediate +1];

        int left = 0, right = 0;

        for (int a = 0; a < size; a++) {
            if (py[a].getX() <= midPoint.getX())
                leftpart[left++] = py[a];
            else
                rightpart[right++] = py[a];
        }

        double lengthl = divideAndConquer(px, leftpart, intermediate);
        double lengthr = divideAndConquer(px, rightpart, size - intermediate);

        double smallest = Math.min(lengthl, lengthr);

        Point[] midlane = new Point[size];
        int midlength = 0;
        for (int a = 0; a < size; a++)
            if (Math.abs(py[a].getX() - midPoint.getX()) < smallest){
                midlane[midlength] = py[a];
                midlength++;
            }

        return Math.min(smallest, midlaneClosest(midlane, midlength, smallest));
    }

    public static double midlaneClosest (Point[] p, int length, double currentMin){
        double newMin = currentMin;

        for (int a = 0; a < length; ++a)
            for (int b = a + 1; (b < length) && ((p[b].getY() - p[a].getY()) < newMin); ++b)
                if (p[a].distance(p[b]) < newMin)
                    newMin = p[a].distance(p[b]);

        return newMin;
    }
}

class Point{
    private int x;
    private int y;
    private int z;

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public double distance(Point second){
        return Math.sqrt(Math.pow((this.getX()-second.getX()),2) +
                Math.pow((this.getY()-second.getY()),2) +
                Math.pow((this.getZ()-second.getZ()),2));
    }
}