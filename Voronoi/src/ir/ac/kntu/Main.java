package ir.ac.kntu;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class Main extends JFrame {
    static BufferedImage bufferedImage;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of cells : ");
        new Main(scanner.nextInt()).setVisible(true);
        scanner.close();
    }

    public Main(int numberOfCells) {
        int width = 1600;
        int height = 900;
        setTitle("Voronoi");
        setSize(width, height);
        setResizable(false);
        Point[] points = new Point[numberOfCells];
        int[] colors = new int[numberOfCells];
        for (int i = 0; i < numberOfCells; i++) {
            points[i] = new Point(RandomHelper.nextInt(width), RandomHelper.nextInt(height));
            colors[i] = RandomHelper.nextInt(567982150);
        }
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int n = 0;
                for (int k = 0; k < numberOfCells; k++) {
                    if (Point.distance(points[k].getX(), i, points[k].getY(), j) < Point.distance(points[n].getX(), i, points[n].getY(), j)) {
                        n = k;
                    }
                }
                bufferedImage.setRGB(i, j, colors[n]);
            }
        }

        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setColor(Color.BLACK);
        for (int i = 0; i < numberOfCells; i++) {
            graphics2D.fill(new Ellipse2D.Double(points[i].getX() - 2.5, points[i].getY() - 2.5, 5, 5));
        }

    }

    public void paint(Graphics graphics) {
        graphics.drawImage(bufferedImage, 0, 0, this);
    }
}

class Point{
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static double distance(int x1, int x2, int y1, int y2) {
        x1 -= x2;
        y1 -= y2;
        return Math.sqrt(x1 * x1 + y1 * y1);
    }
}