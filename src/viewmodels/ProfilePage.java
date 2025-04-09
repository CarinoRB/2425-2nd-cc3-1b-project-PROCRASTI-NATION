import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.awt.image.BufferedImage;

public class ProfilePage extends JPanel {
    private final Color HEADER_COLOR = new Color(245, 245, 250);
    private final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private final Font SECTION_TITLE_FONT = new Font("Segoe UI", Font.BOLD, 18);
    private final Font PROFILE_INFO_FONT = new Font("Segoe UI", Font.PLAIN, 16);
    
    public ProfilePage() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Header panel with title
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(HEADER_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        JLabel titleLabel = new JLabel("My Profile");
        titleLabel.setFont(HEADER_FONT);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        add(headerPanel, BorderLayout.NORTH);
        
        // Main content panel with user info
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setBackground(Color.WHITE);
        mainContent.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        // Profile avatar and basic info
        JPanel profileHeaderPanel = createProfileHeader();
        mainContent.add(profileHeaderPanel);
        mainContent.add(Box.createVerticalStrut(25));
        
        // Personal information section
        mainContent.add(createSection("Personal Information", new String[][] {
            {"Full Name:", "Jaron Puyao"},
            {"Email Address:", "jaronpwet@example.com"},
            {"Phone Number:", "+63 912 345 6789"},
            {"Date of Birth:", "January 15, 1990"},
            {"Address:", "Camp 7, Baguio City, Philippines"}
        }));
        mainContent.add(Box.createVerticalStrut(20));
        
        // Account information section
        mainContent.add(createSection("Account Information", new String[][] {
            {"Member Since:", "March 2025"},
            {"Membership Type:", "Premium"},
            {"Account Status:", "Active"},
            {"Last Login:", "Today, 7:45 AM"}
        }));
        mainContent.add(Box.createVerticalStrut(20));
        
        // Vehicle preferences section
        mainContent.add(createSection("Preferences", new String[][] {
            {"Preferred Car Type:", "Basta may gulong"},
            {"Favorite Locations:", "Benguet, Baguio City"},
            {"Payment Method:", "Credit Card (•••• 1234)"}
        }));
        
        // Wrap in scrollpane
        JScrollPane scrollPane = new JScrollPane(mainContent);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        add(scrollPane, BorderLayout.CENTER);
        
        // Bottom panel with edit button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 30));
        
        JButton editButton = new JButton("Edit Profile");
        editButton.setBackground(new Color(66, 133, 244));
        editButton.setForeground(Color.WHITE);
        editButton.setFocusPainted(false);
        editButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        bottomPanel.add(editButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createProfileHeader() {
        JPanel panel = new JPanel(new BorderLayout(20, 0));
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        panel.setAlignmentX(LEFT_ALIGNMENT);
        
        // Profile avatar
        ImageIcon defaultAvatar = createDefaultAvatar(100);
        JLabel avatarLabel = new JLabel(defaultAvatar);
        avatarLabel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        panel.add(avatarLabel, BorderLayout.WEST);
        
        // Basic user info beside avatar
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.setBackground(Color.WHITE);
        userInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        JLabel nameLabel = new JLabel("Jaron Puyao");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel emailLabel = new JLabel("jaronpwet@example.com");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        emailLabel.setForeground(new Color(100, 100, 100));
        emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        statsPanel.add(createStatLabel("15", "Rentals"));
        statsPanel.add(createStatLabel("4.8", "Rating"));
        
        userInfoPanel.add(nameLabel);
        userInfoPanel.add(Box.createVerticalStrut(5));
        userInfoPanel.add(emailLabel);
        userInfoPanel.add(Box.createVerticalStrut(15));
        userInfoPanel.add(statsPanel);
        
        panel.add(userInfoPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createStatLabel(String value, String label) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel textLabel = new JLabel(label);
        textLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textLabel.setForeground(new Color(120, 120, 120));
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(valueLabel);
        panel.add(textLabel);
        
        return panel;
    }
    
    private JPanel createSection(String title, String[][] fields) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setAlignmentX(LEFT_ALIGNMENT);
        
        // Section title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(SECTION_TITLE_FONT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        titleLabel.setAlignmentX(LEFT_ALIGNMENT);
        panel.add(titleLabel);
        
        // Add a separator
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        separator.setAlignmentX(LEFT_ALIGNMENT);
        panel.add(separator);
        panel.add(Box.createVerticalStrut(15));
        
        // Add fields
        for (String[] field : fields) {
            panel.add(createInfoField(field[0], field[1]));
            panel.add(Box.createVerticalStrut(12));
        }
        
        return panel;
    }
    
    private JPanel createInfoField(String label, String value) {
        JPanel panel = new JPanel(new BorderLayout(15, 0));
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(PROFILE_INFO_FONT);
        labelComponent.setForeground(new Color(100, 100, 100));
        labelComponent.setPreferredSize(new Dimension(150, 25));
        
        JLabel valueComponent = new JLabel(value);
        valueComponent.setFont(PROFILE_INFO_FONT);
        
        panel.add(labelComponent, BorderLayout.WEST);
        panel.add(valueComponent, BorderLayout.CENTER);
        
        return panel;
    }
    
    private ImageIcon createDefaultAvatar(int size) {
        // Create a simple circular avatar placeholder
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        
        // Enable anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw circle background
        g2d.setColor(new Color(66, 133, 244));
        g2d.fillOval(0, 0, size, size);
        
        // Draw PANG PROFILE
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Segoe UI", Font.BOLD, size / 3));
        FontMetrics fm = g2d.getFontMetrics();
        String initials = "JP";
        int textWidth = fm.stringWidth(initials);
        int textHeight = fm.getHeight();
        g2d.drawString(initials, (size - textWidth) / 2, (size + textHeight / 3) / 2);
        
        g2d.dispose();
        
        return new ImageIcon(image);
    }
} 