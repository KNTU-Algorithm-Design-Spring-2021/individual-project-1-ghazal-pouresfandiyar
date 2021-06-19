package ir.ac.kntu;

import java.util.*;

public class Main{
    public static void main(String[] args){
        Point[] allPoints = initialValue();

        Point[] xArr = Arrays.copyOf(allPoints, allPoints.length);
        Point[] yArr = Arrays.copyOf(allPoints, allPoints.length);

        Arrays.sort(xArr , (p1,p2) -> p1.getX()-p2.getX());
        Arrays.sort(yArr , (p1,p2) -> p1.getY()-p2.getY());

        System.out.println("The minimum distance is : " + divideAndConquer(xArr, yArr, allPoints.length));
    }

    public static Point[] initialValue(){
        Point[] array = {   new Point(500,800,200),
                            new Point(0,0,0),
                            new Point(10,90,100),
                            new Point(179,990,10),
                            new Point(0,0,1) };

        return array;
    }


    public static double closest(Point points[], int n ){
        double closestDist = points[0].distance(points[1]); // Give a very big number to find minimum distances.
        for (int x = 0; x < n; x++){
            for (int y = x + 1; y < n; y++){
                if (points[x].distance(points[y]) < closestDist)
                    closestDist = points[x].distance(points[y]);
            }
        }
        return closestDist;
    }

    public static double divideAndConquer (Point px[], Point py[], int size){
        if (size <= 3)
            return closest(px, size);
        int intermediate = size / 2;
        Point midPoint = px[intermediate];

        Point[] leftSide = new Point[intermediate + 1];
        Point[] rightSide = new Point[size - intermediate +1];

        int left = 0, right = 0;

        for (int a = 0; a < size; a++) {
            if (py[a].getX() <= midPoint.getX())
                leftSide[left++] = py[a];
            else
                rightSide[right++] = py[a];
        }

        double lengthl = divideAndConquer(px, leftSide, intermediate);
        double lengthr = divideAndConquer(px, rightSide, size - intermediate);

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

    public double distance(Point second){
        return Math.sqrt(Math.pow((this.getX()-second.getX()),2) +
                Math.pow((this.getY()-second.getY()),2) +
                Math.pow((this.getZ()-second.getZ()),2));
    }
}