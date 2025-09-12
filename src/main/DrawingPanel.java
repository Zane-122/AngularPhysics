package main;
import javax.swing.JPanel;

import main.components.Gravity;
import main.objects.SquareObject;
import main.objects.Object;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Optional;

class DrawingPanel extends JPanel {
    Scale scale = new Scale();

    Object square = new SquareObject();
    

    double velocity = 0;

    public DrawingPanel() {
        (square).addComponent(new Gravity(Optional.empty()));
        
        
        var loop = new javax.swing.Timer((int) Constants.SimulationConstants.TIME_STEP, new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                square.update();
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
        square.draw(g);
    }
}