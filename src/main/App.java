package main;
import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        // Create the frame
        JFrame frame = new JFrame("Angular Physics");

        // Set the frame size and configure its properties
        frame.setSize(Constants.WindowConstants.WIDTH, Constants.WindowConstants.HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); 
        frame.setResizable(false);

        // Add the panel to the frame
        DrawingPanel panel = new DrawingPanel();
        frame.add(panel);

        // Make the frame visible
        frame.setVisible(true);
    }
}