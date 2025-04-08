import javax.swing.*;
import java.awt.*;

public class ProfilePage extends JPanel {
    public ProfilePage() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("My Profile");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        add(titleLabel, BorderLayout.NORTH);
        
        // Add placeholder content
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 20, 5, 20);
        
        contentPanel.add(new JLabel("Name: John Doe"), gbc);
        contentPanel.add(new JLabel("Email: john.doe@example.com"), gbc);
        contentPanel.add(new JLabel("Member since: January 2025"), gbc);
        
        add(contentPanel, BorderLayout.CENTER);
    }
} 