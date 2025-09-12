package main.components;

import java.awt.geom.Point2D;

import main.Constants;
import main.objects.Object;

public class Collision2D extends Component {
     
    public void update(Object object) {
        Point2D[] points = object.getShapeObject().getPoints();

        for (Point2D point : points) {
            if (point.getY() > Constants.WindowConstants.HEIGHT) {
                
            }
        }
    }
}
