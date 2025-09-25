package main;
import javax.swing.JPanel;

import main.shapes.Polygon2D;
import main.Constants.Vector2D;
import main.components.Gravity;
import main.objects.Object;  
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

class DrawingPanel extends JPanel {
    Scale scale = new Scale();
    final double centerX = Constants.WindowConstants.WIDTH / Constants.PhysicsConstants.PIXELS_TO_METERS / 2;
    final double centerY = Constants.WindowConstants.HEIGHT / Constants.PhysicsConstants.PIXELS_TO_METERS / 2;

    // Object triangle = new Object(new Polygon2D(3, 10000, new Point2D.Double(75,20)), 1, 0);
    Object moon = new Object(new Polygon2D(15, 1737000, new Point2D.Double(centerX - 384400000, centerY)), 1, 7.34767309e22, 1);
    Object earth = new Object(new Polygon2D(15, 6371000.0, new Point2D.Double(centerX, centerY)), 1251325215, 5.972e+24, 2);

    double velocity = 0;

    double speed = 10000000;

    public DrawingPanel() {
        moon.addComponent(new Gravity());
        earth.addComponent(new Gravity());

        moon.setVelocity(new Vector2D(1022, 270));
        // triangle.addComponent(new Gravity());
        // triangle.setVelocity(new Vector2D(45, 0));
        // triangle.addComponent(new Gravity())

        Constants.objects.add(earth);
        Constants.objects.add(moon);
        // Constants.objects.add(triangle);
        // Constants.objects.add(triangle);

        var loop = new javax.swing.Timer((int) (Constants.SimulationConstants.TIME_STEP / speed), new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                for (Object object : Constants.objects) {
                    object.update();
                }
                repaint();
            }
        });

        // Start the loop
        loop.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 

        g.setColor(Color.BLACK);
        
        for (Object object : Constants.objects) {
            object.getShapeObject().draw(g);
        }
    }
}