package main.components;

import java.util.Optional;

import main.Constants;
import main.Constants.PhysicsConstants;
import main.Constants.Vector2D;
import main.objects.Object;

public class Gravity extends Component {
    // private double force = PhysicsConstants.GRAVITY;

    // public Gravity(Optional<Double> force) {
    //     if (force.isPresent()) {
    //         this.force = force.get();
    //     }
    // }

    @Override
    public void update(Object object) {
        double dt = 1.0 / Constants.SimulationConstants.FPS; // seconds per frame
        for (int i = 0; i < Constants.objects.size(); i++) {
            Object obj = Constants.objects.get(i);
            if (object.getId() != obj.getId()) {
                // displacement (meters)
                double dx = obj.getPosition().getX() - object.getPosition().getX();
                double dy = obj.getPosition().getY() - object.getPosition().getY();

                double r2 = dx*dx + dy*dy;
                double r = Math.sqrt(r2);

                if (r > 1e-6) { // avoid divide by zero
                    double m2 = obj.getMass(); // kg
                    double a = (Constants.PhysicsConstants.G * m2) / r2;

                    // direction (radians)
                    double angle = Math.atan2(dy, dx);

                    System.out.println("Angle: " + angle);
                    System.out.println("Magnitude: " + a);

                    // velocity change (m/s)
                    object.addVelocity(new Vector2D(a * dt, Math.toDegrees(angle)));
                }
            }
        }       
    }
}
