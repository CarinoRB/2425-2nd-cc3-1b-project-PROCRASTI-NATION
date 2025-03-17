import java.io.*;
import javax.swing.*;

public class Main{
    public static void main(String[]args){
        JFrame frame = new JFrame();

        // Creating instance of JButton
        JButton button = new JButton("Team Procrasti-Nation");

        // x axis, y axis, width, height
        button.setBounds(150, 200, 220, 50);

        // adding button in JFrame
        frame.add(button);

        // 400 width and 500 height
        frame.setSize(500, 600);

        // using no layout managers
        frame.setLayout(null);

        // making the frame visible
        frame.setVisible(true);
    }
}
