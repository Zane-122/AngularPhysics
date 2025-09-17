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

    Object triangle = new Object(new Polygon2D(3, 3, new Point2D.Double(75,20)), 1, 0);
    Object square = new Object(new Polygon2D(4, 3, new Point2D.Double(75, 30)), 1, 1);
    Object circle = new Object(new Polygon2D(10, 5, new Point2D.Double(75, 50)), 99999999, 2);

    double velocity = 0;

    double speed = 0.1;

    public DrawingPanel() {
        square.addComponent(new Gravity());
        circle.addComponent(new Gravity());
        triangle.addComponent(new Gravity());

        square.setVelocity(new Vector2D(30, 0));
        triangle.setVelocity(new Vector2D(35, 0));
        // triangle.addComponent(new Gravity());

        Constants.objects.add(circle);
        Constants.objects.add(square);
        Constants.objects.add(triangle);
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

        scale.draw(g);

        g.setColor(Color.BLACK);
        
        for (Object object : Constants.objects) {
            object.getShapeObject().draw(g);
        }
    }
}