package main.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

/**
 * ALL UNITS OF SHAPES ARE IN METERS
 */
public interface Shape {
    void draw(Graphics g);
    void setColor(Color color);

    void rotate(double angle);
    void translate(double dx, double dy);

    Point2D[] getPoints();
    Point2D getCenter();
    
    double getSideLength();
}
