import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.image.BufferedImage;

public class NotificationsPage extends JPanel {
    private final Color HEADER_COLOR = new Color(245, 245, 250);
    private final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private final Color UNREAD_COLOR = new Color(240, 247, 255);
    private final Color READ_COLOR = Color.WHITE;
    private final Color ACCENT_COLOR = new Color(66, 133, 244);
    
    public NotificationsPage() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Header panel with title and action buttons
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(HEADER_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        JLabel titleLabel = new JLabel("Notifications");
        titleLabel.setFont(HEADER_FONT);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        // Add action buttons to header
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actionPanel.setBackground(HEADER_COLOR);
        
        JButton markAllReadBtn = createActionButton("Mark All Read");
        JButton clearAllBtn = createActionButton("Clear All");
        
        actionPanel.add(markAllReadBtn);
        actionPanel.add(clearAllBtn);
        headerPanel.add(actionPanel, BorderLayout.EAST);
        
        add(headerPanel, BorderLayout.NORTH);
        
        // Main content panel for notifications list
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setBackground(Color.WHITE);
        mainContent.setBorder(BorderFactory.createEmptyBorder(10, 30, 20, 30));
        
        // Sample notifications - would come from real data in a production app
        if (hasNotifications()) {
            // Add filter options
            JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
            filterPanel.setBackground(Color.WHITE);
            filterPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
            filterPanel.setAlignmentX(LEFT_ALIGNMENT);
            
            String[] filterOptions = {"All", "Unread", "Bookings", "Promos", "System"};
            JComboBox<String> filterCombo = new JComboBox<>(filterOptions);
            filterCombo.setPreferredSize(new Dimension(150, 30));
            
            JLabel filterLabel = new JLabel("Filter: ");
            filterLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            
            filterPanel.add(filterLabel);
            filterPanel.add(filterCombo);
            mainContent.add(filterPanel);
            
            // Add a separator
            JSeparator separator = new JSeparator();
            separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
            separator.setAlignmentX(LEFT_ALIGNMENT);
            mainContent.add(separator);
            mainContent.add(Box.createVerticalStrut(15));
            
            // Add sample notifications
            mainContent.add(createNotificationItem(
                "Booking Confirmed", 
                "Your booking for Toyota Vios (ABC-123) has been confirmed for March 12, 2025.",
                "10 minutes ago",
                "booking",
                true
            ));
            
            mainContent.add(createNotificationItem(
                "Payment Successful", 
                "Your payment of ₱2,500 for booking #12345 has been processed successfully.",
                "2 hours ago",
                "payment",
                true
            ));
            
            mainContent.add(createNotificationItem(
                "Weekend Special Promo", 
                "Get 15% off on all SUV rentals this weekend. Use code WEEKEND15 at checkout.",
                "Yesterday",
                "promo",
                false
            ));
            
            mainContent.add(createNotificationItem(
                "Booking Reminder", 
                "Your booking for Honda City (XYZ-789) is scheduled for tomorrow at 9:00 AM.",
                "2 days ago",
                "reminder",
                false
            ));
            
            mainContent.add(createNotificationItem(
                "Rate Your Experience", 
                "How was your recent ride with our Toyota Fortuner? Please take a moment to rate your experience.",
                "1 week ago",
                "rating",
                false
            ));
        } else {
            // Empty state
            JPanel emptyStatePanel = createEmptyStatePanel();
            mainContent.add(emptyStatePanel);
        }
        
        // Wrap in scrollpane
        JScrollPane scrollPane = new JScrollPane(mainContent);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private JButton createActionButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setForeground(ACCENT_COLOR);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
    
    private JPanel createEmptyStatePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        
        // Create icon for empty state
        JLabel iconLabel = new JLabel(createEmptyIcon());
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel titleLabel = new JLabel("No New Notifications");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        
        JLabel subtitleLabel = new JLabel("You're all caught up! Check back later for updates.");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(120, 120, 120));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(iconLabel);
        panel.add(titleLabel);
        panel.add(subtitleLabel);
        
        return panel;
    }
    
    private ImageIcon createEmptyIcon() {
        // Create a simple notification bell icon
        int size = 100;
        Image image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        
        // Enable anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw a light circle background
        g2d.setColor(new Color(240, 240, 240));
        g2d.fillOval(0, 0, size, size);
        
        // Draw a bell shape
        g2d.setColor(new Color(200, 200, 200));
        int bellSize = size/2;
        g2d.fillOval(size/4, size/4, bellSize, bellSize);
        
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(size/2, size/2 + bellSize/2, size/2, size/2 + bellSize/2 + 10);
        
        g2d.dispose();
        
        return new ImageIcon(image);
    }
    
    private JPanel createNotificationItem(String title, String message, String time, String type, boolean isUnread) {
        JPanel panel = new JPanel(new BorderLayout(10, 5));
        panel.setBackground(isUnread ? UNREAD_COLOR : READ_COLOR);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Left icon indicator
        JPanel iconPanel = new JPanel(new BorderLayout());
        iconPanel.setOpaque(false);
        iconPanel.setPreferredSize(new Dimension(30, 30));
        
        JLabel iconLabel = new JLabel(getIconForType(type));
        iconPanel.add(iconLabel, BorderLayout.CENTER);
        
        // Main content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        
        // Title with unread indicator if needed
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        titlePanel.setOpaque(false);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titlePanel.add(titleLabel);
        
        if (isUnread) {
            JLabel unreadDot = new JLabel("•");
            unreadDot.setFont(new Font("Segoe UI", Font.BOLD, 20));
            unreadDot.setForeground(ACCENT_COLOR);
            titlePanel.add(unreadDot);
        }
        
        contentPanel.add(titlePanel);
        
        // Message
        JLabel messageLabel = new JLabel("<html><body style='width: 300px'>" + message + "</body></html>");
        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messageLabel.setForeground(new Color(80, 80, 80));
        messageLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        contentPanel.add(messageLabel);
        
        // Time
        JLabel timeLabel = new JLabel(time);
        timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        timeLabel.setForeground(new Color(150, 150, 150));
        timeLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        contentPanel.add(timeLabel);
        
        // Action menu
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.setOpaque(false);
        
        JButton menuButton = new JButton("···");
        menuButton.setBorderPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.setFocusPainted(false);
        menuButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        menuButton.setForeground(new Color(100, 100, 100));
        menuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        actionPanel.add(menuButton);
        
        // Add components to main panel
        panel.add(iconPanel, BorderLayout.WEST);
        panel.add(contentPanel, BorderLayout.CENTER);
        panel.add(actionPanel, BorderLayout.EAST);
        
        // Add hover effect
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(new Color(245, 245, 245));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(isUnread ? UNREAD_COLOR : READ_COLOR);
            }
        });
        
        return panel;
    }
    
    private ImageIcon getIconForType(String type) {
        int size = 24;
        Image image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        
        // Enable anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Color iconColor;
        
        // Pick color based on notification type
        switch (type) {
            case "booking":
                iconColor = new Color(66, 133, 244); // Blue
                break;
            case "payment":
                iconColor = new Color(52, 168, 83); // Green
                break;
            case "promo":
                iconColor = new Color(251, 188, 5); // Yellow
                break;
            case "reminder":
                iconColor = new Color(234, 67, 53); // Red
                break;
            default:
                iconColor = new Color(150, 150, 150); // Gray
        }
        
        // Draw colored circle
        g2d.setColor(iconColor);
        g2d.fillOval(0, 0, size, size);
        
        g2d.dispose();
        
        return new ImageIcon(image);
    }
    
    // This would be replaced with actual logic to check if the user has notifications
    private boolean hasNotifications() {
        return true; // For demo, always show notifications
    }
} 