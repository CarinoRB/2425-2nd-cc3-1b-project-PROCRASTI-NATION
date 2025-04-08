import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class SettingsPage extends JPanel {
    private final Color HEADER_COLOR = new Color(245, 245, 250);
    private final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private final Font SETTING_LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 16);
    
    public SettingsPage() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Header panel with title
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(HEADER_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        JLabel titleLabel = new JLabel("Settings");
        titleLabel.setFont(HEADER_FONT);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        add(headerPanel, BorderLayout.NORTH);
        
        // Main content with settings
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setBackground(Color.WHITE);
        mainContent.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        // Add settings categories
        mainContent.add(createCategoryPanel("Appearance", new JComponent[]{
            createSettingItem("Dark Mode", createToggleSwitch(false)),
            createSettingItem("Font Size", createComboBox(new String[]{"Small", "Medium", "Large"})),
            createSettingItem("Color Theme", createComboBox(new String[]{"Default Blue", "Dark Gray", "Light Gray"}))
        }));
        
        mainContent.add(Box.createVerticalStrut(15));
        
        mainContent.add(createCategoryPanel("Notifications", new JComponent[]{
            createSettingItem("Email Notifications", createToggleSwitch(true)),
            createSettingItem("SMS Notifications", createToggleSwitch(false)),
            createSettingItem("Push Notifications", createToggleSwitch(true))
        }));
        
        mainContent.add(Box.createVerticalStrut(15));
        
        mainContent.add(createCategoryPanel("Account", new JComponent[]{
            createSettingItem("Language", createComboBox(new String[]{"English", "Tagalog", "Spanish"})),
            createSettingItem("Region", createComboBox(new String[]{"Philippines", "United States", "Europe"})),
            createSettingItem("Auto-logout after inactivity", createToggleSwitch(true))
        }));
        
        // Wrap in scrollpane to handle overflow
        JScrollPane scrollPane = new JScrollPane(mainContent);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        add(scrollPane, BorderLayout.CENTER);
        
        // Add save button at bottom
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 30));
        
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createCategoryPanel(String title, JComponent[] settingItems) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setAlignmentX(LEFT_ALIGNMENT);
        
        // Category title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panel.add(titleLabel);
        
        // Add a separator
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        panel.add(separator);
        panel.add(Box.createVerticalStrut(10));
        
        // Add settings
        for (JComponent item : settingItems) {
            panel.add(item);
            panel.add(Box.createVerticalStrut(15));
        }
        
        return panel;
    }
    
    private JPanel createSettingItem(String label, JComponent control) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(20, 0));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        
        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(SETTING_LABEL_FONT);
        panel.add(labelComponent, BorderLayout.WEST);
        panel.add(control, BorderLayout.EAST);
        
        return panel;
    }
    
    private JToggleButton createToggleSwitch(boolean initialState) {
        JToggleButton toggle = new JToggleButton(initialState ? "On" : "Off");
        toggle.setSelected(initialState);
        toggle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        toggle.setForeground(Color.WHITE);
        toggle.setBackground(initialState ? new Color(76, 175, 80) : new Color(158, 158, 158));
        toggle.setFocusPainted(false);
        toggle.setBorderPainted(false);
        toggle.setCursor(new Cursor(Cursor.HAND_CURSOR));
        toggle.setPreferredSize(new Dimension(80, 30));
        
        toggle.addItemListener(e -> {
            boolean selected = e.getStateChange() == ItemEvent.SELECTED;
            toggle.setText(selected ? "On" : "Off");
            toggle.setBackground(selected ? new Color(76, 175, 80) : new Color(158, 158, 158));
        });
        
        return toggle;
    }
    
    private JComboBox<String> createComboBox(String[] options) {
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setPreferredSize(new Dimension(150, 30));
        comboBox.setBackground(Color.WHITE);
        return comboBox;
    }
} 