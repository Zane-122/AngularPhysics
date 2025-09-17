package main.objects;

import main.Constants;
import main.Constants.PhysicsConstants;
import main.Constants.Vector2D;
import main.components.Component;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import main.shapes.Shape;

public class Object {
    protected Shape shape;

    // Velocity in m/s
    protected Vector2D velocity = new Vector2D(0, 0);;

    // In kilograms per meter squared
    protected double density;

    // In meters squared
    protected double area = 1000000000;

    // The components applied to the object
    protected ArrayList<Component> components = new ArrayList<Component>();

    // The ID of the object
    protected int id;
    
    public Object(Shape shape, double density, int id) {
        this.shape = shape;
        this.density = density;
        this.id = id;
    }

    public void update() {
        double dt = 1.0 / Constants.SimulationConstants.FPS;

        for (Component c : components) {
            c.update(this);
        }

        // Convert velocity (m/s) into pixel displacement this frame
        double dx = Math.cos(Math.toRadians(velocity.direction())) * velocity.magnitude() * dt * PhysicsConstants.PIXELS_TO_METERS;
        double dy = Math.sin(Math.toRadians(velocity.direction())) * velocity.magnitude() * dt * PhysicsConstants.PIXELS_TO_METERS;

        this.shape.translate(dx, dy);
    }

    public void draw(Graphics g) {
        shape.draw(g);
    }

    public Object addComponent(Component c) {
        components.add(c);

        return this;
    };

    public Shape getShapeObject() {
        return this.shape;
    }

    /**
     * Use this rarely
     * @param velocity the velocity you want to set
     */
    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    /**
     * Add velocity to the current velocity
     * @param velocity the velocity you want to add
     */
    public void addVelocity(Vector2D velocity) {
        // Convert this.velocity to x/y
        double x1 = (this.velocity.magnitude()) * Math.cos(Math.toRadians(this.velocity.direction()));
        double y1 = (this.velocity.magnitude()) * Math.sin(Math.toRadians(this.velocity.direction()));

        // Convert input velocity to x/y
        double x2 = (velocity.magnitude()) * Math.cos(Math.toRadians(velocity.direction()));
        double y2 = (velocity.magnitude()) * Math.sin(Math.toRadians(velocity.direction()));

        // Add components
        double x = x1 + x2;
        double y = y1 + y2;

        // Convert back to vector
        double mag = Math.sqrt(x * x + y * y);
        double angle = Math.toDegrees(Math.atan2(y, x));

        this.velocity = new Vector2D(mag, angle);
    }

    public Vector2D getVelocity() {
        return this.velocity;
    }

    public int getId() {
        return this.id;
    }

    public Point2D getPosition() {
        return shape.getCenter();
    }

    public double getMass() {
        return area * density;
    }
}
