package main.objects;

import java.awt.geom.Point2D;

import main.shapes.Polygon2D;

public class SquareObject extends Object {
    public SquareObject() {
        super(new Polygon2D(4 , 1, new Point2D.Double(2, 0)));
    }
}
