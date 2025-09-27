package main;
import javax.swing.JPanel;

import main.Constants.WindowConstants;
import main.components.Gravity;
import main.Constants.Planets;
import main.Constants.Vector2D;
import main.objects.Object;
import main.shapes.Polygon2D;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

class DrawingPanel extends JPanel {
    Scale scale = new Scale();

    double speed = 1;
    
    public DrawingPanel() {

        Constants.objects.add(Planets.earth(new Point2D.Double(WindowConstants.centerX, WindowConstants.centerY)));
        Constants.objects.add(Planets.moon(new Point2D.Double(WindowConstants.centerX - 384.4e6, WindowConstants.centerY)).addVelocity(new Vector2D(1020, 90)));

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