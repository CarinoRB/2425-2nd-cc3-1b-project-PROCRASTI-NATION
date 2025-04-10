import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class SideNavigation extends JPanel {
    private final Color BG_COLOR = Color.WHITE;
    private final Color SELECTED_COLOR = new Color(66, 133, 244);
    private final Color HOVER_COLOR = new Color(45, 45, 45);
    private final Color NOTIFICATION_ALERT_COLOR = new Color(234, 67, 53);  // Red color for notifications
    private final Font MENU_FONT = new Font("Segoe UI Emoji", Font.PLAIN, 14);
    private final int EXPANDED_WIDTH = 220;
    private final int COLLAPSED_WIDTH = 60;
    
    private boolean isCollapsed = false;
    private JPanel menuItemsPanel;
    private JButton toggleButton;
    private MainUI mainUI;
    private JButton notificationButton; // Reference to notification button
    private Timer flashTimer; // Timer for flashing effect
    private boolean isFlashing = false; // Track flashing state
    private int flashCount = 0; // Track number of flashes
    
    public SideNavigation(MainUI mainUI) {
        this.mainUI = mainUI;
        initializeUI();
        initializeFlashTimer();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(BG_COLOR);
        setPreferredSize(new Dimension(COLLAPSED_WIDTH, getHeight()));
        
        // Create toggle button at the top
        toggleButton = createToggleButton();
        add(toggleButton, BorderLayout.NORTH);
        
        // Create menu items panel
        menuItemsPanel = new JPanel();
        menuItemsPanel.setLayout(new BoxLayout(menuItemsPanel, BoxLayout.Y_AXIS));
        menuItemsPanel.setBackground(BG_COLOR);
        
        // Add menu items
        addMenuItem("🏠 ", "home", true);
        addMenuItem("🔔 ", "notifications", false);
        addMenuItem("📅 ", "bookings", false);
        addMenuItem("👤 ", "profile", false);
        addMenuItem("⚙️ ", "settings", false);
        
        // Add some spacing at the bottom
        menuItemsPanel.add(Box.createVerticalGlue());
        
        // Wrap menuItemsPanel in a scroll pane
        JScrollPane scrollPane = new JScrollPane(menuItemsPanel);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private JButton createToggleButton() {
        JButton button = new JButton("≡");
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setForeground(Color.WHITE);
        button.setBackground(BG_COLOR);
        button.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setContentAreaFilled(false);
        
        button.addActionListener(e -> toggleNavigation());
        
        return button;
    }
    
    private void initializeFlashTimer() {
        // Flash interval of 500ms (half a second)
        flashTimer = new Timer(350, e -> {
            if (notificationButton != null) {
                if (isFlashing) {
                    // Toggle between red and normal background
                    if (notificationButton.getBackground().equals(NOTIFICATION_ALERT_COLOR)) {
                        // Set it back to normal
                        boolean isSelected = mainUI.getCurrentPage().equals("notifications");
                        notificationButton.setBackground(isSelected ? SELECTED_COLOR : BG_COLOR);
                    } else {
                        // Set to notification alert color
                        notificationButton.setBackground(NOTIFICATION_ALERT_COLOR);
                    }
                    
                    flashCount++;
                    if (flashCount >= 10) { // Flash 5 times (10 changes)
                        stopFlashing();
                    }
                }
            }
        });
    }
    
    private void addMenuItem(String text, String pageName, boolean isSelected) {
        JButton menuItem = new JButton(text);
        menuItem.setFont(MENU_FONT);
        menuItem.setForeground(Color.BLACK);
        menuItem.setBackground(isSelected ? SELECTED_COLOR : BG_COLOR);
        menuItem.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        menuItem.setHorizontalAlignment(SwingConstants.LEFT);
        menuItem.setFocusPainted(false);
        menuItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menuItem.setMaximumSize(new Dimension(EXPANDED_WIDTH, 50));
        
        // Store reference to notification button
        if (pageName.equals("notifications")) {
            notificationButton = menuItem;
        }
        
        // Add hover effect
        menuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isSelected && !isFlashing) {
                    menuItem.setBackground(HOVER_COLOR);
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (!isSelected && !isFlashing) {
                    menuItem.setBackground(BG_COLOR);
                }
            }
        });
        
        // Add click handler
        menuItem.addActionListener(e -> {
            mainUI.showPage(pageName);
            
            // If this is the notification button and it's flashing, stop flashing
            if (pageName.equals("notifications") && isFlashing) {
                stopFlashing();
            }
            
            // Update selected state of all menu items
            updateSelectedMenuItem(pageName);
        });
        
        menuItemsPanel.add(menuItem);
    }
    
    /**
     * Updates the selected state of all menu items
     * @param selectedPage the currently selected page
     */
    private void updateSelectedMenuItem(String selectedPage) {
        for (Component c : menuItemsPanel.getComponents()) {
            if (c instanceof JButton) {
                JButton btn = (JButton) c;
                String text = btn.getText();
                String itemPage = getPageNameFromEmoji(text.substring(0, 2));
                
                // Set background based on whether this item is selected
                if (itemPage.equals(selectedPage)) {
                    btn.setBackground(SELECTED_COLOR);
                } else if (!isFlashing || !itemPage.equals("notifications")) {
                    btn.setBackground(BG_COLOR);
                }
            }
        }
    }
    
    /**
     * Starts flashing the notification menu item
     */
    public void flashNotificationMenuItem() {
        if (notificationButton != null && !isFlashing) {
            isFlashing = true;
            flashCount = 0;
            flashTimer.start();
        }
    }
    
    /**
     * Stops flashing the notification menu item
     */
    private void stopFlashing() {
        if (isFlashing) {
            isFlashing = false;
            flashTimer.stop();
            
            // Reset to appropriate color
            if (notificationButton != null) {
                boolean isSelected = mainUI.getCurrentPage().equals("notifications");
                notificationButton.setBackground(isSelected ? SELECTED_COLOR : BG_COLOR);
            }
        }
    }
    
    /**
     * Gets the page name from an emoji
     */
    private String getPageNameFromEmoji(String emoji) {
        switch (emoji.trim()) {
            case "🏠": return "home";
            case "🔔": return "notifications";
            case "📅": return "bookings";
            case "👤": return "profile";
            case "⚙️": return "settings";
            default: return "";
        }
    }
    
    private void toggleNavigation() {
        isCollapsed = !isCollapsed;
        int targetWidth = isCollapsed ? COLLAPSED_WIDTH : EXPANDED_WIDTH;
        setPreferredSize(new Dimension(targetWidth, getHeight()));
        
        // Update menu items text
        for (Component c : menuItemsPanel.getComponents()) {
            if (c instanceof JButton) {
                JButton btn = (JButton) c;
                String text = btn.getText();
                if (isCollapsed) {
                    // Show only emoji
                    btn.setText(text.substring(0, 2));
                } else {
                    // Show full text (you might want to store the original text somewhere)
                    String fullText = getFullText(text.substring(0, 2));
                    btn.setText(fullText);
                }
            }
        }
        
        revalidate();
        repaint();
    }
    
    private String getFullText(String emoji) {
        switch (emoji) {
            case "🏠": return "🏠 Home";
            case "🔔": return "🔔 Notifications";
            case "📅": return "📅 Bookings";
            case "👤": return "👤 Profile";
            case "⚙️": return "⚙️ Settings";
            default: return emoji;
        }
    }
} 