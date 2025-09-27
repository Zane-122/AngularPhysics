package main;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import main.components.Gravity;
import main.objects.Object;
import main.shapes.Polygon2D;

public final class Constants {
    public static class WindowConstants {
        public static final int WIDTH = 1280;
        public static final int HEIGHT = 720;

        public static final double centerX = WIDTH / PhysicsConstants.PIXELS_TO_METERS / 2;
        public static final double centerY = HEIGHT / PhysicsConstants.PIXELS_TO_METERS / 2;

    }

    public static class PhysicsConstants {
        public static final double GRAVITY = 9.81;
        public static final double PIXELS_TO_METERS = 0.000001; // how many pixels in a meter
        public static final double G = 6.67430e-11;
    }

    public static class SimulationConstants {
        public static final double FPS = 60;
        public static final double TIME_STEP = 1000 / FPS; 

        public static double speedMultiplier = 100000;
        public static final double dt = (1 / FPS) * speedMultiplier;
    }

    public record Vector2D(double magnitude, double direction) {}

    static public ArrayList<Object> objects = new ArrayList<Object>();

    public static class Planets {
        public static final Object earth(Point2D position) {
            return new Object(new Polygon2D(15, 6378000, position), 5.9722e+24, -1).addComponent(new Gravity());
        } 

        public static final Object moon(Point2D position) {
            return new Object(new Polygon2D(15, 1740000, position), 7.34767309e+22, -2).addComponent(new Gravity());
        } 

        public static final Object pluto(Point2D position) {
            return new Object(new Polygon2D(15, 1118100, position), 1.3e22, -3).addComponent(new Gravity());
        } 

        public static final Object jupiter(Point2D position) {
            return new Object(new Polygon2D(15, 70e6, position), 1.898e+27, -4).addComponent(new Gravity());
        } 
    }
}
