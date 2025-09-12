package main;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Written by AI because I don't want to write it myself.
 */
public class Scale {
    public void draw(Graphics g) {
         // Draw alternating green and blue solid scale on the left side
         int leftSideX = 0; // 10 pixels from the left edge
         int lineWidth = (int) Constants.PhysicsConstants.PIXELS_TO_METERS / 5; // Width of the measurement lines
         int totalMeters = (int) (Constants.WindowConstants.HEIGHT / Constants.PhysicsConstants.PIXELS_TO_METERS);
         
         for (var i = 0; i < totalMeters + 1; i++) {
             int y = i * (int) Constants.PhysicsConstants.PIXELS_TO_METERS;
             int nextY = (i + 1) * (int) Constants.PhysicsConstants.PIXELS_TO_METERS;
             
             // Alternate between green and blue
             if (i % 2 == 0) {
                 g.setColor(Color.BLACK);
             } else {
                 g.setColor(Color.WHITE);
             }
             
             // Draw vertical rectangle connecting to the next line
             g.fillRect(leftSideX, y, lineWidth, nextY - y);
         }

         g.setColor(Color.BLACK);
         g.drawRect(leftSideX, 0, lineWidth, Constants.WindowConstants.HEIGHT);
    }
}
