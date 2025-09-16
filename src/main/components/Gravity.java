package main.components;

import java.util.Optional;

import main.Constants;
import main.Constants.PhysicsConstants;
import main.Constants.Vector2D;
import main.objects.Object;

public class Gravity extends Component{
    private double force = PhysicsConstants.GRAVITY;

    public Gravity(Optional<Double> force) {
        if (force.isPresent()) {
            this.force = force.get();
        }
    }

    @Override
    public void update(Object object) {
        double dt = 1.0 / Constants.SimulationConstants.FPS; // seconds per frame
                
        // update velocity (pixels/sec)
        object.addVelocity(new Vector2D(force * dt, 90));
    }
    
}
