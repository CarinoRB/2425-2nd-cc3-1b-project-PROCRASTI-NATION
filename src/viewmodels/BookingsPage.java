

import javax.swing.*;
import java.awt.*;

public class BookingsPage extends JPanel {
    public BookingsPage() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("My Bookings");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        add(titleLabel, BorderLayout.NORTH);
        
        // Add placeholder content
        JLabel placeholderLabel = new JLabel("No active bookings");
        placeholderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(placeholderLabel, BorderLayout.CENTER);
    }
} 