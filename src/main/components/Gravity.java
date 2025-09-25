package main.components;

import main.Constants;
import main.Constants.*;
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
        double dt = 1.0 / SimulationConstants.FPS; 
        for (int i = 0; i < Constants.objects.size(); i++) {
            Object obj = Constants.objects.get(i);
            if (object.getId() != obj.getId()) {
                double dx = obj.getPosition().getX() - object.getPosition().getX();
                double dy = obj.getPosition().getY() - object.getPosition().getY();

                double d2 = dx*dx + dy*dy;
                double d = Math.sqrt(d2);

                if (d > 1e-6) { // avoid divide by zero
                    double m2 = obj.getMass(); // kg

                    double eps = 250; 
                    
                    double softened = d2 + eps*eps;
                    double a = (PhysicsConstants.G * m2) / softened;

                    // direction (radians)
                    double angle = Math.atan2(dy, dx);

                    // velocity change (m/s)
                    object.addVelocity(new Vector2D(a * dt, Math.toDegrees(angle)));
                }
            }
        }       
    }
}
