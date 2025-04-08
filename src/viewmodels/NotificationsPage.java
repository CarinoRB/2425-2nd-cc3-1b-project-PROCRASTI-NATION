import javax.swing.*;
import java.awt.*;

public class NotificationsPage extends JPanel {
    public NotificationsPage() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("Notifications");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        add(titleLabel, BorderLayout.NORTH);
        
        // Add placeholder content
        JLabel placeholderLabel = new JLabel("No new notifications");
        placeholderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(placeholderLabel, BorderLayout.CENTER);
    }
} 