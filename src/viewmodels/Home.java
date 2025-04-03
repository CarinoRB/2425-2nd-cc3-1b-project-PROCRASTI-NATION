package viewmodels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.AbstractBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Home extends JFrame {
    
    public Home() {
        // Set up the main frame
        setTitle("Car Rental Service");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));
        setBackground(Color.WHITE);

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(getWidth(), 80));
        headerPanel.setBackground(new Color(30, 136, 229));
        headerPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        
        // Logo and Title Panel
        JPanel logoTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        logoTitlePanel.setOpaque(false);
        
        JLabel logoLabel = new JLabel("🚗");
        logoLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        logoTitlePanel.add(logoLabel);
        
        JLabel titleLabel = new JLabel("Car Rental Service");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        logoTitlePanel.add(titleLabel);
        
        // User Menu Panel
        JPanel userMenuPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        userMenuPanel.setOpaque(false);
        
        JButton loginButton = new JButton("Login");
        styleButton(loginButton, new Color(224, 224, 224), Color.DARK_GRAY);
        
        JButton registerButton = new JButton("Register");
        styleButton(registerButton, new Color(46, 204, 113), Color.BLACK);
        registerButton.setVisible(true);
        
        userMenuPanel.add(loginButton);
        userMenuPanel.add(registerButton);
        
        headerPanel.add(logoTitlePanel, BorderLayout.WEST);
        headerPanel.add(userMenuPanel, BorderLayout.EAST);

        // Main Content Panel
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBackground(Color.WHITE);
        mainContentPanel.setBorder(new EmptyBorder(40, 60, 40, 60));

        // Welcome Section Wrapper
        JPanel welcomeWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        welcomeWrapper.setBackground(Color.WHITE);
        welcomeWrapper.setMaximumSize(new Dimension(Short.MAX_VALUE, 150));

        JPanel welcomeTextContainer = new JPanel();
        welcomeTextContainer.setLayout(new BoxLayout(welcomeTextContainer, BoxLayout.Y_AXIS));
        welcomeTextContainer.setBackground(Color.WHITE);

        JLabel welcomeLabel = new JLabel("Welcome to Car Rental Service");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        welcomeLabel.setForeground(new Color(33, 33, 33));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeTextContainer.add(welcomeLabel);

        welcomeTextContainer.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel infoLabel = new JLabel("<html><div style='text-align: center; width: 600px;'>"
                + "Find the perfect car for your needs. Browse our collection of modern, "
                + "well-maintained vehicles and book your next ride with ease.</div></html>");
        infoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        infoLabel.setForeground(new Color(97, 97, 97));
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeTextContainer.add(infoLabel);
        
        welcomeWrapper.add(welcomeTextContainer);
        mainContentPanel.add(welcomeWrapper);

        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // Featured Cars Panel
        JPanel featuredPanel = new JPanel(new GridLayout(1, 3, 30, 0));
        featuredPanel.setBackground(Color.WHITE);
        featuredPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        featuredPanel.setMaximumSize(new Dimension(1080, 400));

        // Sample car data
        String[][] carData = {
            {"Toyota Supra", "50", "2 Seater", "With Aircon", "Automatic", "4.8", "15"},
            {"Honda Civic Type R", "45", "4 Seater", "With Aircon", "Manual", "4.6", "23"},
            {"Nissan GT-R", "60", "2 Seater", "With Aircon", "Automatic", "4.9", "19"}
        };

        for (String[] carInfo : carData) {
            featuredPanel.add(createCarCard(carInfo));
        }

        mainContentPanel.add(featuredPanel);

        // Footer Panel
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(new Color(33, 33, 33));
        footerPanel.setPreferredSize(new Dimension(getWidth(), 60));
        JLabel footerLabel = new JLabel("© 2025 Car Rental Service. All rights reserved.");
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        footerLabel.setForeground(Color.WHITE);
        footerPanel.add(footerLabel);

        // Add all panels to the main frame
        add(headerPanel, BorderLayout.NORTH);
        add(mainContentPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        // Action listeners
        loginButton.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, "Login functionality to be implemented")
        );

        registerButton.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, "Registration functionality to be implemented")
        );
    }

    private void styleButton(JButton button, Color bgColor, Color fgColor) {
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private String generateStars(double rating) {
        int fullStars = (int) Math.round(rating);
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            stars.append(i < fullStars ? "⭐" : "☆");
        }
        return stars.toString();
    }

    private JPanel createCarCard(String[] carInfo) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        
        // Create a content panel with padding that will go inside the card
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(25, 25, 25, 25)); // Add padding
        contentPanel.setBackground(Color.WHITE);
        
        // Set card background and border
        card.setBorder(new CompoundBorder(
            new DropShadowBorder(new Color(0, 0, 0, 40), 5, 10, 0.3f, 12, true, true, true, true),
            new LineBorder(new Color(220, 220, 220), 1)
        ));
        card.setBackground(Color.WHITE);
        
        // Car image with more padding
        JLabel carImage = new JLabel("🚗");
        carImage.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        carImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        carImage.setBorder(new EmptyBorder(15, 0, 15, 0)); // Add vertical padding around the car image
        contentPanel.add(carImage);
        
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Car name and price
        JLabel carName = new JLabel(carInfo[0]);
        carName.setFont(new Font("Segoe UI", Font.BOLD, 20));
        carName.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(carName);
        
        JLabel priceLabel = new JLabel("$" + carInfo[1] + "/24 Hours");
        priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        priceLabel.setForeground(new Color(30, 136, 229));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(priceLabel);
        
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Car details
        JLabel detailsLabel = new JLabel("Car Details:");
        detailsLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        detailsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(detailsLabel);
        
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        // Details list
        String[] details = {carInfo[2], carInfo[3], carInfo[4]};
        for (String detail : details) {
            JLabel detailItem = new JLabel("• " + detail);
            detailItem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            detailItem.setForeground(new Color(97, 97, 97));
            detailItem.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentPanel.add(detailItem);
            contentPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        }
        
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(Box.createVerticalGlue());
        
        // Bottom panel for rating and rent button
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 0));
        bottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setOpaque(true);
        
        // Rating panel
        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        ratingPanel.setOpaque(false);
        JLabel ratingStars = new JLabel(generateStars(Double.parseDouble(carInfo[5])));
        ratingStars.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        JLabel ratingText = new JLabel(String.format(" (%s)", carInfo[6]));
        ratingText.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        ratingText.setForeground(new Color(97, 97, 97));
        ratingPanel.add(ratingStars);
        ratingPanel.add(ratingText);
        
        // Rent button
        JButton rentButton = new JButton("Rent Now");
        rentButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        rentButton.setBackground(new Color(46, 204, 113));
        rentButton.setForeground(Color.BLACK);
        rentButton.setFocusPainted(false);
        rentButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rentButton.addActionListener(e -> 
            JOptionPane.showMessageDialog(null, "Booking system to be implemented")
        );
        
        bottomPanel.add(ratingPanel, BorderLayout.WEST);
        bottomPanel.add(rentButton, BorderLayout.EAST);
        
        contentPanel.add(bottomPanel);
        
        // Add the content panel to the card
        card.add(contentPanel);
        
        return card;
    }

    private class DropShadowBorder extends AbstractBorder {
        private Color shadowColor;
        private int shadowSize;
        private int cornerRadius;
        private float shadowOpacity;
        private boolean topOpaque, leftOpaque, bottomOpaque, rightOpaque;
        
        public DropShadowBorder(Color shadowColor, int shadowSize, int cornerRadius, float shadowOpacity, int distance, 
                                boolean topOpaque, boolean leftOpaque, boolean bottomOpaque, boolean rightOpaque) {
            this.shadowColor = shadowColor;
            this.shadowSize = shadowSize;
            this.cornerRadius = cornerRadius;
            this.shadowOpacity = shadowOpacity;
            this.topOpaque = topOpaque;
            this.leftOpaque = leftOpaque;
            this.bottomOpaque = bottomOpaque;
            this.rightOpaque = rightOpaque;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int shadowX = x;
            int shadowY = y;

            // Create a round rectangle for the component's shape
            RoundRectangle2D roundRect = new RoundRectangle2D.Double(x, y, width - shadowSize, height - shadowSize, cornerRadius, cornerRadius);

            // Paint shadow layers for a blur effect approximation
            for (int i = shadowSize; i >= 1; i--) {
                float alpha = shadowOpacity * (1.0f - (float)i / shadowSize);
                g2.setColor(new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), (int)(alpha * 255)));
                
                int offset = i;
                RoundRectangle2D shadowRect = new RoundRectangle2D.Double(
                    shadowX + offset, 
                    shadowY + offset, 
                    width - shadowSize * 2, 
                    height - shadowSize * 2, 
                    cornerRadius, cornerRadius
                );
                g2.fill(shadowRect);
            }

            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            int top = topOpaque ? 0 : shadowSize;
            int left = leftOpaque ? 0 : shadowSize;
            int bottom = bottomOpaque ? 0 : shadowSize;
            int right = rightOpaque ? 0 : shadowSize;
            return new Insets(top, left, bottom, right);
        }
        
         @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.top = topOpaque ? 0 : shadowSize;
            insets.left = leftOpaque ? 0 : shadowSize;
            insets.bottom = bottomOpaque ? 0 : shadowSize;
            insets.right = rightOpaque ? 0 : shadowSize;
            return insets;
        }
    }

    // This main method can be used for direct testing of the Home class
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            new Home().setVisible(true);
        });
    }
    
    // We'll keep this main method for testing purposes, but in production,
    // the application will be launched from Main.java
}
