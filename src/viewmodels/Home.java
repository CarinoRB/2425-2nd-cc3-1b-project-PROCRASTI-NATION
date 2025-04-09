import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Home extends JPanel {
    // Colors and styling - matching the AuthForm
    private final Color BACKGROUND_COLOR = Color.WHITE;
    private final Color PRIMARY_COLOR = new Color(66, 133, 244); // Blue accent color
    private final Color SECONDARY_COLOR = new Color(46, 204, 113); // Green for action buttons
    private final Color TEXT_COLOR = new Color(33, 33, 33);
    private final Color TEXT_SECONDARY_COLOR = new Color(97, 97, 97);
    private final Color LIGHT_BORDER_COLOR = new Color(230, 230, 230);
    
    private final Font HEADER_FONT = new Font("Arial", Font.BOLD, 28);
    private final Font SUBHEADER_FONT = new Font("Arial", Font.BOLD, 22);
    private final Font REGULAR_FONT = new Font("Arial", Font.PLAIN, 14);
    private final Font SMALL_FONT = new Font("Arial", Font.PLAIN, 12);
    
    public Home() {
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(0, 0));
        setBackground(BACKGROUND_COLOR);

        // Create the main content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(BACKGROUND_COLOR);
        
        // Welcome Section with modern styling
        JPanel welcomeSection = createWelcomeSection();
        contentPanel.add(welcomeSection);
        
        contentPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // Featured Cars Panel with consistent design language
        JPanel featuredCarsPanel = createFeaturedCarsPanel();
        contentPanel.add(featuredCarsPanel);
        
        // Create a scroll pane to make the content scrollable
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        // Add the scroll pane to the main panel
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(1200, 70));
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        
        // Logo and Title Panel
        JPanel logoTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        logoTitlePanel.setOpaque(false);
        
        JLabel logoLabel = new JLabel("🚗");
        logoLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        logoTitlePanel.add(logoLabel);
        
        JLabel titleLabel = new JLabel("Car Rental Service");
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(Color.WHITE);
        logoTitlePanel.add(titleLabel);
        
        // User Menu Panel
        JPanel userMenuPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        userMenuPanel.setOpaque(false);
        
        
        return headerPanel;
    }
    
    private JPanel createWelcomeSection() {
        JPanel welcomeWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        welcomeWrapper.setBackground(BACKGROUND_COLOR);
        welcomeWrapper.setMaximumSize(new Dimension(Short.MAX_VALUE, 150));

        JPanel welcomeTextContainer = new JPanel();
        welcomeTextContainer.setLayout(new BoxLayout(welcomeTextContainer, BoxLayout.Y_AXIS));
        welcomeTextContainer.setBackground(BACKGROUND_COLOR);

        JLabel welcomeLabel = new JLabel("Welcome to Car Rental Service");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 32));
        welcomeLabel.setForeground(TEXT_COLOR);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeTextContainer.add(welcomeLabel);

        welcomeTextContainer.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel infoLabel = new JLabel("<html><div style='text-align: center; width: 600px;'>"
                + "Find the perfect car for your needs. Browse our collection of modern, "
                + "well-maintained vehicles and book your next ride with ease.</div></html>");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        infoLabel.setForeground(TEXT_SECONDARY_COLOR);
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeTextContainer.add(infoLabel);
        
        welcomeWrapper.add(welcomeTextContainer);
        return welcomeWrapper;
    }
    
    private JPanel createFeaturedCarsPanel() {
        // Section title with consistent styling
        JPanel featuredTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        featuredTitlePanel.setBackground(BACKGROUND_COLOR);
        featuredTitlePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
        
        JLabel featuredLabel = new JLabel("Featured Cars");
        featuredLabel.setFont(SUBHEADER_FONT);
        featuredLabel.setForeground(TEXT_COLOR);
        featuredTitlePanel.add(featuredLabel);
        
        // Container for title and cards
        JPanel featuredContainer = new JPanel();
        featuredContainer.setLayout(new BoxLayout(featuredContainer, BoxLayout.Y_AXIS));
        featuredContainer.setBackground(BACKGROUND_COLOR);
        featuredContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        featuredContainer.add(featuredTitlePanel);
        featuredContainer.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Featured Cars Grid - changed to 4 rows of 3 columns
        JPanel featuredPanel = new JPanel(new GridLayout(4, 3, 30, 30));
        featuredPanel.setBackground(BACKGROUND_COLOR);
        featuredPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        featuredPanel.setMaximumSize(new Dimension(1080, 1600));

        // Sample car data with 12 cars
        String[][] carData = {
            {"Toyota Supra", "50", "2 Seater", "With Aircon", "Automatic", "4.8", "15"},
            {"Honda Civic Type R", "45", "4 Seater", "With Aircon", "Manual", "4.6", "23"},
            {"Nissan GT-R", "60", "2 Seater", "With Aircon", "Automatic", "4.9", "19"},
            {"Ford Mustang", "55", "4 Seater", "With Aircon", "Automatic", "4.7", "18"},
            {"BMW M3", "65", "4 Seater", "With Aircon", "Automatic", "4.8", "20"},
            {"Mercedes-AMG C63", "70", "4 Seater", "With Aircon", "Automatic", "4.7", "16"},
            {"Audi R8", "80", "2 Seater", "With Aircon", "Automatic", "4.9", "12"},
            {"Porsche 911", "85", "2+2 Seater", "With Aircon", "Automatic", "5.0", "25"},
            {"Chevrolet Camaro", "52", "4 Seater", "With Aircon", "Manual", "4.5", "17"},
            {"Subaru WRX STI", "48", "5 Seater", "With Aircon", "Manual", "4.6", "22"},
            {"Lamborghini Huracan", "120", "2 Seater", "With Aircon", "Automatic", "5.0", "10"},
            {"Ferrari 488 GTB", "130", "2 Seater", "With Aircon", "Automatic", "5.0", "8"}
        };

        for (String[] carInfo : carData) {
            featuredPanel.add(createCarCard(carInfo));
        }
        
        featuredContainer.add(featuredPanel);
        
        // Add some padding at the bottom
        featuredContainer.add(Box.createRigidArea(new Dimension(0, 40)));
        
        return featuredContainer;
    }
    
    // private JPanel createFooterPanel() {
    //     JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    //     footerPanel.setBackground(TEXT_COLOR);
    //     footerPanel.setPreferredSize(new Dimension(getWidth(), 50));
        
    //     JLabel footerLabel = new JLabel("© 2025 Car Rental Service. All rights reserved.");
    //     footerLabel.setFont(new Font("Arial", Font.PLAIN, 13));
    //     footerLabel.setForeground(Color.WHITE);
    //     footerPanel.add(footerLabel);
        
    //     return footerPanel;
    // }

    private JButton createStyledButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 8, 8));
                g2.dispose();
                
                super.paintComponent(g);
            }
        };
        
        button.setFont(REGULAR_FONT);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return button;
    }

    private String generateStars(double rating) {
        int fullStars = (int) Math.round(rating);
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            stars.append(i < fullStars ? "🌟" : "⭐");
        }
        return stars.toString();
    }

    private JPanel createCarCard(String[] carInfo) {
        // Main card panel
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        
        // Create a content panel with padding
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        contentPanel.setBackground(BACKGROUND_COLOR);
        
        // Apply modern card styling with subtle shadow
        card.setBorder(new CompoundBorder(
            new ModernShadowBorder(Color.BLACK, 10, 8, 0.08f),
            new LineBorder(LIGHT_BORDER_COLOR, 1, true)
        ));
        card.setBackground(BACKGROUND_COLOR);
        
        // Car image with consistent styling
        JLabel carImage = new JLabel("🚗");
        carImage.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        carImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        carImage.setBorder(new EmptyBorder(10, 0, 15, 0));
        contentPanel.add(carImage);
        
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Car name and price - styled to match the login form
        JLabel carName = new JLabel(carInfo[0]);
        carName.setFont(new Font("Arial", Font.BOLD, 18));
        carName.setForeground(TEXT_COLOR);
        carName.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(carName);
        
        JLabel priceLabel = new JLabel("$" + carInfo[1] + "/24 Hours");
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        priceLabel.setForeground(PRIMARY_COLOR);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(priceLabel);
        
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Car details with refined styling
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(BACKGROUND_COLOR);
        detailsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel detailsLabel = new JLabel("Car Details");
        detailsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailsLabel.setForeground(TEXT_COLOR);
        detailsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.add(detailsLabel);
        
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        
        // Details list with consistent styling
        String[] details = {carInfo[2], carInfo[3], carInfo[4]};
        for (String detail : details) {
            JLabel detailItem = new JLabel("• " + detail);
            detailItem.setFont(REGULAR_FONT);
            detailItem.setForeground(TEXT_SECONDARY_COLOR);
            detailItem.setAlignmentX(Component.CENTER_ALIGNMENT);
            detailsPanel.add(detailItem);
            detailsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        
        contentPanel.add(detailsPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(Box.createVerticalGlue());
        
        // Bottom panel for rating and rent button
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 0));
        bottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        bottomPanel.setBackground(BACKGROUND_COLOR);
        
        // Rating panel
        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        ratingPanel.setOpaque(false);
        
        JLabel ratingStars = new JLabel(generateStars(Double.parseDouble(carInfo[5])));
        ratingStars.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        ratingStars.setForeground(new Color(255, 180, 0)); // Gold star color
        
        JLabel ratingText = new JLabel(String.format(" (%s)", carInfo[6]));
        ratingText.setFont(SMALL_FONT);
        ratingText.setForeground(TEXT_SECONDARY_COLOR);
        
        ratingPanel.add(ratingStars);
        ratingPanel.add(ratingText);
        
        JButton rentButton = createStyledButton("Rent Now", PRIMARY_COLOR, Color.WHITE);
        rentButton.setFont(new Font("Arial", Font.BOLD, 13));
        rentButton.addActionListener(e -> 
            showRentalModal(carInfo[0], carInfo[1], carInfo[2], carInfo[3], carInfo[4])
        );
        
        bottomPanel.add(ratingPanel, BorderLayout.WEST);
        bottomPanel.add(rentButton, BorderLayout.EAST);
        
        contentPanel.add(bottomPanel);
        
        // Add the content panel to the card
        card.add(contentPanel);
        
        return card;
    }

    private void showRentalModal(String carName, String price, String seats, String aircon, String transmission) {
        // Create custom dialog
        JDialog dialog = new JDialog((Frame)SwingUtilities.getWindowAncestor(this), "Rent a Car", true);
        dialog.setSize(700, 550);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        
        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        
        // Car image and name header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(BACKGROUND_COLOR);
        headerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel carEmoji = new JLabel("🚗");
        carEmoji.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        carEmoji.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel nameLabel = new JLabel(carName);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setForeground(TEXT_COLOR);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        headerPanel.add(carEmoji);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        headerPanel.add(nameLabel);
        
        // Separator
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        separator.setForeground(LIGHT_BORDER_COLOR);
        
        // Car details section
        JPanel detailsPanel = new JPanel(new GridLayout(0, 2, 10, 15));
        detailsPanel.setBackground(BACKGROUND_COLOR);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        detailsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Car specifications
        addDetailRow(detailsPanel, "Vehicle Type:", getVehicleType(seats, transmission));
        addDetailRow(detailsPanel, "Seating Capacity:", seats);
        addDetailRow(detailsPanel, "Transmission:", transmission);
        addDetailRow(detailsPanel, "Air Conditioning:", aircon);
        addDetailRow(detailsPanel, "Rate per Day:", "$" + price);
        
        // Location and dates info
        addDetailRow(detailsPanel, "Availability:", "Available Now");
        addDetailRow(detailsPanel, "Pickup Location:", "Manila Main Branch");
        
        // Create rental duration input
        JPanel durationPanel = new JPanel(new BorderLayout(10, 0));
        durationPanel.setOpaque(false);
        
        JLabel durationLabel = new JLabel("Rental Duration (days):");
        durationLabel.setFont(new Font("Arial", Font.BOLD, 14));
        durationLabel.setForeground(TEXT_SECONDARY_COLOR);
        
        // Create spinner model with min 1, max 30, default 3 days
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(3, 1, 30, 1);
        JSpinner durationSpinner = new JSpinner(spinnerModel);
        durationSpinner.setPreferredSize(new Dimension(80, 25));
        JComponent editor = durationSpinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) editor;
            spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
        }
        
        JPanel spinnerWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        spinnerWrapper.setOpaque(false);
        spinnerWrapper.add(durationSpinner);
        
        durationPanel.add(durationLabel, BorderLayout.WEST);
        durationPanel.add(spinnerWrapper, BorderLayout.EAST);
        
        // Calculate initial dates based on default duration
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("MMM dd, yyyy");
        
        calendar.add(java.util.Calendar.DATE, 3);
        String pickupDate = dateFormat.format(calendar.getTime());
        
        calendar.add(java.util.Calendar.DATE, (Integer)spinnerModel.getValue());
        String returnDate = dateFormat.format(calendar.getTime());
        
        // Create labels that will be updated
        JLabel pickupDateLabel = new JLabel(pickupDate + ", 10:00 AM");
        pickupDateLabel.setFont(REGULAR_FONT);
        pickupDateLabel.setForeground(TEXT_COLOR);
        
        JLabel returnDateLabel = new JLabel(returnDate + ", 10:00 AM");
        returnDateLabel.setFont(REGULAR_FONT);
        returnDateLabel.setForeground(TEXT_COLOR);
        
        // Add date rows
        JPanel pickupDatePanel = new JPanel(new BorderLayout());
        pickupDatePanel.setOpaque(false);
        JLabel pickupTextLabel = new JLabel("Earliest Pickup:");
        pickupTextLabel.setFont(new Font("Arial", Font.BOLD, 14));
        pickupTextLabel.setForeground(TEXT_SECONDARY_COLOR);
        pickupDatePanel.add(pickupTextLabel, BorderLayout.WEST);
        pickupDatePanel.add(pickupDateLabel, BorderLayout.EAST);
        
        JPanel returnDatePanel = new JPanel(new BorderLayout());
        returnDatePanel.setOpaque(false);
        JLabel returnTextLabel = new JLabel("Return Date:");
        returnTextLabel.setFont(new Font("Arial", Font.BOLD, 14));
        returnTextLabel.setForeground(TEXT_SECONDARY_COLOR);
        returnDatePanel.add(returnTextLabel, BorderLayout.WEST);
        returnDatePanel.add(returnDateLabel, BorderLayout.EAST);
        
        // Calculate initial price (3 days rental by default)
        int pricePerDay = Integer.parseInt(price);
        final int[] days = {(Integer)spinnerModel.getValue()};
        final int[] totalPrice = {pricePerDay * days[0]};
        final int[] insurancePrice = {25};
        final int[] taxesPrice = {(int)(totalPrice[0] * 0.12)};
        final int[] finalTotal = {totalPrice[0] + insurancePrice[0] + taxesPrice[0]};
        
        // References to pricing labels that need updating
        final JLabel[] rentalFeeValueLabel = {new JLabel("$" + totalPrice[0])};
        final JLabel[] taxesValueLabel = {new JLabel("$" + taxesPrice[0])};
        final JLabel[] totalValueLabel = {new JLabel("$" + finalTotal[0])};
        
        // Pricing summary panel
        JPanel pricingPanel = new JPanel();
        pricingPanel.setLayout(new BoxLayout(pricingPanel, BoxLayout.Y_AXIS));
        pricingPanel.setBackground(new Color(245, 247, 250));
        pricingPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(LIGHT_BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        pricingPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel summaryLabel = new JLabel("Pricing Summary");
        summaryLabel.setFont(new Font("Arial", Font.BOLD, 16));
        summaryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel rentalFeePanel = new JPanel(new BorderLayout());
        rentalFeePanel.setOpaque(false);
        rentalFeePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        final JLabel[] rentalFeeDaysLabel = {new JLabel("Rental Fee (" + days[0] + " days):")};
        rentalFeePanel.add(rentalFeeDaysLabel[0], BorderLayout.WEST);
        rentalFeePanel.add(rentalFeeValueLabel[0], BorderLayout.EAST);
        
        JPanel insurancePanel = new JPanel(new BorderLayout());
        insurancePanel.setOpaque(false);
        insurancePanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        insurancePanel.add(new JLabel("Insurance:"), BorderLayout.WEST);
        insurancePanel.add(new JLabel("$" + insurancePrice[0]), BorderLayout.EAST);
        
        JPanel taxPanel = new JPanel(new BorderLayout());
        taxPanel.setOpaque(false);
        taxPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        taxPanel.add(new JLabel("Taxes & Fees:"), BorderLayout.WEST);
        taxPanel.add(taxesValueLabel[0], BorderLayout.EAST);
        
        JSeparator priceSeparator = new JSeparator();
        priceSeparator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        priceSeparator.setForeground(LIGHT_BORDER_COLOR);
        
        JPanel totalPanel = new JPanel(new BorderLayout());
        totalPanel.setOpaque(false);
        totalPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        
        JLabel totalLabel = new JLabel("Total:");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        totalValueLabel[0].setFont(new Font("Arial", Font.BOLD, 16));
        totalValueLabel[0].setForeground(PRIMARY_COLOR);
        
        totalPanel.add(totalLabel, BorderLayout.WEST);
        totalPanel.add(totalValueLabel[0], BorderLayout.EAST);
        
        pricingPanel.add(summaryLabel);
        pricingPanel.add(rentalFeePanel);
        pricingPanel.add(insurancePanel);
        pricingPanel.add(taxPanel);
        pricingPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        pricingPanel.add(priceSeparator);
        pricingPanel.add(totalPanel);
        
        // Add listener to update prices when duration changes
        durationSpinner.addChangeListener(e -> {
            // Update days value
            days[0] = (Integer)durationSpinner.getValue();
            
            // Update return date
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.add(java.util.Calendar.DATE, 3); // pickup date is fixed 3 days from now
            
            // Set return date based on duration
            cal.add(java.util.Calendar.DATE, days[0]);
            String newReturnDate = dateFormat.format(cal.getTime());
            returnDateLabel.setText(newReturnDate + ", 10:00 AM");
            
            // Recalculate prices
            totalPrice[0] = pricePerDay * days[0];
            taxesPrice[0] = (int)(totalPrice[0] * 0.12);
            finalTotal[0] = totalPrice[0] + insurancePrice[0] + taxesPrice[0];
            
            // Update price labels
            rentalFeeDaysLabel[0].setText("Rental Fee (" + days[0] + " days):");
            rentalFeeValueLabel[0].setText("$" + totalPrice[0]);
            taxesValueLabel[0].setText("$" + taxesPrice[0]);
            totalValueLabel[0].setText("$" + finalTotal[0]);
        });
        
        // Add elements to the details panel
        detailsPanel.add(durationPanel);
        detailsPanel.add(new JLabel("")); // Empty cell for grid alignment
        detailsPanel.add(pickupTextLabel);
        detailsPanel.add(pickupDateLabel);
        detailsPanel.add(returnTextLabel);
        detailsPanel.add(returnDateLabel);
        
        // Action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(REGULAR_FONT);
        cancelButton.setForeground(TEXT_SECONDARY_COLOR);
        cancelButton.setBorderPainted(false);
        cancelButton.setFocusPainted(false);
        cancelButton.setContentAreaFilled(false);
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelButton.addActionListener(e -> dialog.dispose());
        
        JButton confirmButton = createStyledButton("Confirm Booking", SECONDARY_COLOR, Color.WHITE);
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
        confirmButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        confirmButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(dialog, 
                "Your booking for " + carName + " has been confirmed!\n" +
                "Pickup: " + pickupDateLabel.getText() + "\n" +
                "Return: " + returnDateLabel.getText() + "\n" +
                "Total: " + totalValueLabel[0].getText(),
                "Booking Confirmed", 
                JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose();
        });
        
        buttonPanel.add(cancelButton);
        buttonPanel.add(confirmButton);
        
        // Add all components to content panel
        contentPanel.add(headerPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(separator);
        contentPanel.add(detailsPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        contentPanel.add(pricingPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(buttonPanel);
        
        // Wrap the content panel in a scroll pane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        // Add the scroll pane to the dialog
        dialog.add(scrollPane);
        dialog.setVisible(true);
    }
    
    private void addDetailRow(JPanel panel, String label, String value) {
        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(new Font("Arial", Font.BOLD, 14));
        labelComponent.setForeground(TEXT_SECONDARY_COLOR);
        
        JLabel valueComponent = new JLabel(value);
        valueComponent.setFont(REGULAR_FONT);
        valueComponent.setForeground(TEXT_COLOR);
        
        panel.add(labelComponent);
        panel.add(valueComponent);
    }
    
    private String getVehicleType(String seats, String transmission) {
        if (seats.contains("2")) {
            return "Sports Car";
        } else if (seats.contains("7")) {
            return "SUV";
        } else if (transmission.contains("Manual")) {
            return "Sedan (Manual)";
        } else {
            return "Sedan";
        }
    }

    //PANG SHADOW EFFECTS DI KO RIN TO MAINTINDIHAN!!!
    private class ModernShadowBorder extends AbstractBorder {
        private Color shadowColor;
        private int shadowSize;
        private int cornerRadius;
        private float shadowOpacity;
        
        public ModernShadowBorder(Color shadowColor, int shadowSize, int cornerRadius, float shadowOpacity) {
            this.shadowColor = shadowColor;
            this.shadowSize = shadowSize;
            this.cornerRadius = cornerRadius;
            this.shadowOpacity = shadowOpacity;
        }


        //PANG DESIGN LANG TO DO NOT BOTHER !!! DI KO RIN MAINTINDIHAN
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Create a round rectangle for the component's shape
            RoundRectangle2D roundRect = new RoundRectangle2D.Double(
                x + 2, y + 2, 
                width - shadowSize, height - shadowSize, 
                cornerRadius, cornerRadius
            );

            // Paint multiple subtle shadow layers for a more refined appearance
            for (int i = 0; i < shadowSize; i++) {
                float alpha = shadowOpacity * (1.0f - (float)i / shadowSize);
                g2.setColor(new Color(
                    shadowColor.getRed(), 
                    shadowColor.getGreen(), 
                    shadowColor.getBlue(), 
                    (int)(alpha * 255)
                ));
                
                int offset = i / 2;
                g2.draw(new RoundRectangle2D.Double(
                    x + offset, 
                    y + offset, 
                    width - offset * 2, 
                    height - offset * 2, 
                    cornerRadius, 
                    cornerRadius
                ));
            }

            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(shadowSize, shadowSize, shadowSize, shadowSize);
        }
        
        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.top = shadowSize;
            insets.left = shadowSize;
            insets.bottom = shadowSize;
            insets.right = shadowSize;
            return insets;
        }
    }


    //OWN CLASS FOR TESTING PURPOSES
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Car Rental Service");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 800);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new Home());
            frame.setVisible(true);
        });
    }
}
