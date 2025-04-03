import javax.swing.*;
import viewmodels.Home;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            Home homeUI = new Home();
            homeUI.setVisible(true);
        });
    }
}
