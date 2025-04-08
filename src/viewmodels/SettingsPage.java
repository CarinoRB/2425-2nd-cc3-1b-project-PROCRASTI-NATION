import javax.swing.*;
import java.awt.*;

public class SettingsPage extends JPanel {
    public SettingsPage() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("Settings");
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
        
        // Add some sample settings
        contentPanel.add(createSettingItem("Dark Mode", new JToggleButton("Off")), gbc);
        contentPanel.add(createSettingItem("Notifications", new JToggleButton("On")), gbc);
        contentPanel.add(createSettingItem("Language", new JComboBox<>(new String[]{"English", "Tagalog"})), gbc);
        
        add(contentPanel, BorderLayout.CENTER);
    }
    
    private JPanel createSettingItem(String label, JComponent control) {
        JPanel panel = new JPanel(new BorderLayout(20, 0));
        panel.setBackground(Color.WHITE);
        panel.add(new JLabel(label), BorderLayout.WEST);
        panel.add(control, BorderLayout.EAST);
        return panel;
    }
} 