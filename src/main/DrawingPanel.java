package main;
import javax.swing.JPanel;

import main.shapes.Polygon2D;
import main.components.Gravity;
import main.objects.Object;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

class DrawingPanel extends JPanel {
    Scale scale = new Scale();

    Object square = new Object(new Polygon2D(4, 1, new Point2D.Double(5, 5)), 100000, 1);
    Object circle = new Object(new Polygon2D(10, 1, new Point2D.Double(40, 30)), 100000, 2);

    double velocity = 0;

    public DrawingPanel() {
        square.addComponent(new Gravity());
        circle.addComponent(new Gravity());

        Constants.objects.add(circle);
        Constants.objects.add(square);

        var loop = new javax.swing.Timer((int) Constants.SimulationConstants.TIME_STEP, new ActionListener() {
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