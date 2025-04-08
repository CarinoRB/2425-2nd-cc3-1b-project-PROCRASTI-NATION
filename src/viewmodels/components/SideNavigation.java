import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class SideNavigation extends JPanel {
    private final Color BG_COLOR = Color.WHITE;
    private final Color SELECTED_COLOR = new Color(66, 133, 244);
    private final Color HOVER_COLOR = new Color(45, 45, 45);
    private final Font MENU_FONT = new Font("Segoe UI Emoji", Font.PLAIN, 14);
    private final int EXPANDED_WIDTH = 220;
    private final int COLLAPSED_WIDTH = 60;
    
    private boolean isCollapsed = false;
    private JPanel menuItemsPanel;
    private JButton toggleButton;
    private MainUI mainUI;
    
    public SideNavigation(MainUI mainUI) {
        this.mainUI = mainUI;
        initializeUI();
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
        
        // Add hover effect
        menuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isSelected) {
                    menuItem.setBackground(HOVER_COLOR);
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (!isSelected) {
                    menuItem.setBackground(BG_COLOR);
                }
            }
        });
        
        // Add click handler
        menuItem.addActionListener(e -> mainUI.showPage(pageName));
        
        menuItemsPanel.add(menuItem);
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