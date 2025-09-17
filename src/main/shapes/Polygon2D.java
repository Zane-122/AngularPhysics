package main.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.List;

import main.Constants;
import main.Constants.Vector2D;

public class Polygon2D implements Shape {
    private int sides;
    private double size;

    private Color color = Color.BLACK;

    private Point2D center;

    private Point2D[] point2Ds;
    
    /**
     * @param sides number of sides
     * @param size size of the polygon in meters
     * @param center center of the polygon in meters
     */
    public Polygon2D(int sides, double size, Point2D center) {
        this.sides = sides;
        this.size = size * Constants.PhysicsConstants.PIXELS_TO_METERS;
        this.center = new Point2D.Double(center.getX() * Constants.PhysicsConstants.PIXELS_TO_METERS, center.getY() * Constants.PhysicsConstants.PIXELS_TO_METERS);

        point2Ds = new Point2D[sides];
        rotate(-(((2 * Math.PI)) / this.sides) / 2);
    }

    @Override
    public void draw(Graphics g) { 
        g.setColor(color);
           
        for (var i = 0; i < point2Ds.length; i++) {
            int nextIndex = (i + 1) % point2Ds.length; 
            g.drawLine((int) point2Ds[i].getX(), (int) point2Ds[i].getY(), (int) point2Ds[nextIndex].getX(), (int) point2Ds[nextIndex].getY());
        }
    }

    @Override
    public void rotate(double angle) {
        // Ensure points are generated before rotating
        if (point2Ds[0] == null) {
            generatePoints();
        }
        
        for (var i = 0; i < point2Ds.length; i++) {
            double x = point2Ds[i].getX() - center.getX();
            double y = point2Ds[i].getY() - center.getY();
            
            double rotatedX = x * Math.cos(angle) - y * Math.sin(angle);
            double rotatedY = x * Math.sin(angle) + y * Math.cos(angle);

            point2Ds[i] = new Point2D.Double(
                rotatedX + center.getX(),
                rotatedY + center.getY()
            );
        }
    }

    @Override
    public Point2D[] getPoints() {
        return point2Ds;
    }

    @Override
    public void translate(double dx, double dy) {
        center = new Point2D.Double(center.getX() + dx, center.getY() + dy);
        for (var i = 0; i < point2Ds.length; i++) {
            point2Ds[i] = new Point2D.Double(point2Ds[i].getX() + dx, point2Ds[i].getY() + dy);
        }
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public double getSideLength() {
        return size;
    }

    @Override
    public Point2D getCenter() {
        double sumX = 0;
        double sumY = 0;

        for (Point2D p : point2Ds) {
            // assuming Vector2D has getters like getX() / getY()
            sumX += p.getX();
            sumY += p.getY();
        }

        int n = point2Ds.length;

        return new Point2D.Double(sumX / n, sumY / n); // returns centroid
    }

    private void generatePoints() {
        for (var i = 0; i < sides; i++) {
            point2Ds[i] = new Point2D.Double(
                (size * Math.cos(2 * Math.PI * i / sides)) + center.getX(), 
                (size * Math.sin(2 * Math.PI * i / sides)) + center.getY()
            );
        }
    }
}
