package main;

import java.util.ArrayList;
import main.objects.Object;

public final class Constants {
    public static class WindowConstants {
        public static final int WIDTH = 1280;
        public static final int HEIGHT = 720;
    }

    public static class PhysicsConstants {
        public static final double GRAVITY = 9.81;
        public static final double PIXELS_TO_METERS = 0.000001; // how many pixels in a meter
        public static final double G = 6.67430e-11;
    }

    public static class SimulationConstants {
        public static final double FPS = 60;
        public static final double TIME_STEP = 1000 / FPS; 
    }

    public record Vector2D(double magnitude, double direction) {}

    static public ArrayList<Object> objects = new ArrayList<Object>();
}
