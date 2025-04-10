import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.awt.image.BufferedImage;

public class BookingsPage extends JPanel {
    private final Color HEADER_COLOR = new Color(245, 245, 250);
    private final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private final Color ACCENT_COLOR = new Color(66, 133, 244);
    private final Color ACTIVE_COLOR = new Color(76, 175, 80);
    private final Color COMPLETED_COLOR = new Color(158, 158, 158);
    private final Color CANCELLED_COLOR = new Color(234, 67, 53);
    
    // For storing bookings data
    private JPanel activeContentPanel;
    private JTabbedPane tabbedPane;
    
    public BookingsPage() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Header panel with title and action buttons
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(HEADER_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        JLabel titleLabel = new JLabel("My Bookings");
        titleLabel.setFont(HEADER_FONT);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        // Add action buttons
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        actionPanel.setBackground(HEADER_COLOR);
        
        // JButton newBookingBtn = createPrimaryButton("New Booking");
        // actionPanel.add(newBookingBtn);
        
        headerPanel.add(actionPanel, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);
        
        // Tab panel for different booking states
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabbedPane.setBorder(BorderFactory.createEmptyBorder());
        
        // Active bookings tab
        JPanel activeBookingsPanel = createBookingsPanel(true);
        tabbedPane.addTab("Active", activeBookingsPanel);
        
        // Completed bookings tab
        JPanel completedBookingsPanel = createBookingsPanel(false);
        tabbedPane.addTab("Completed", completedBookingsPanel);
        
        // Cancelled bookings tab
        JPanel cancelledBookingsPanel = createEmptyBookingsPanel("No cancelled bookings", 
            "Any bookings you cancel will appear here.");
        tabbedPane.addTab("Cancelled", cancelledBookingsPanel);
        
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    private JPanel createBookingsPanel(boolean isActive) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        // Create search and filter controls
        JPanel controlsPanel = new JPanel(new BorderLayout());
        controlsPanel.setBackground(Color.WHITE);
        controlsPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        
        // Create search field
        JTextField searchField = new JTextField("Search bookings...");
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        searchField.setForeground(new Color(150, 150, 150));
        
        // Add focus listener to clear placeholder text
        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search bookings...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search bookings...");
                    searchField.setForeground(new Color(150, 150, 150));
                }
            }
        });
        
        // Add filter dropdown
        JPanel filtersPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        filtersPanel.setBackground(Color.WHITE);
        
        JLabel filterLabel = new JLabel("Filter by: ");
        filterLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        String[] filterOptions = {"All Vehicles", "Sedan", "SUV", "Van", "Luxury"};
        JComboBox<String> filterCombo = new JComboBox<>(filterOptions);
        filterCombo.setPreferredSize(new Dimension(150, 36));
        
        filtersPanel.add(filterLabel);
        filtersPanel.add(filterCombo);
        
        controlsPanel.add(searchField, BorderLayout.CENTER);
        controlsPanel.add(filtersPanel, BorderLayout.EAST);
        
        panel.add(controlsPanel, BorderLayout.NORTH);
        
        // Create bookings list
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 30));
        
        // Store reference to active content panel for adding new bookings
        if (isActive) {
            activeContentPanel = contentPanel;
            
            // Add active bookings
            contentPanel.add(createBookingCard(
                "Toyota Vios 2025",
                "Sedan • Manual • 5 Seats",
                "ABC-123",
                "Mar 15, 2025 - Mar 17, 2025",
                "Pickup: Manila Airport Terminal 1",
                "₱2,500",
                "active"
            ));
            
            contentPanel.add(Box.createVerticalStrut(20));
            
            contentPanel.add(createBookingCard(
                "Mitsubishi Montero Sport",
                "SUV • Automatic • 7 Seats",
                "XYZ-789",
                "Apr 10, 2025 - Apr 15, 2025",
                "Pickup: Cebu City Branch",
                "₱15,000",
                "confirmed"
            ));
        } else {
            // Add completed bookings
            contentPanel.add(createBookingCard(
                "Honda City",
                "Sedan • Automatic • 5 Seats",
                "DEF-456",
                "Jan 5, 2025 - Jan 7, 2025",
                "Pickup: Davao City Branch",
                "₱3,000",
                "completed"
            ));
            
            contentPanel.add(Box.createVerticalStrut(20));
            
            contentPanel.add(createBookingCard(
                "Toyota Fortuner",
                "SUV • Automatic • 7 Seats",
                "GHI-789",
                "Feb 12, 2025 - Feb 14, 2025",
                "Pickup: Manila Makati Branch",
                "₱8,500",
                "completed"
            ));
        }
        
        // Wrap in scrollpane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createEmptyBookingsPanel(String title, String subtitle) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        
        // Create icon
        JLabel iconLabel = new JLabel(createEmptyStateIcon());
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        
        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(120, 120, 120));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(iconLabel);
        panel.add(titleLabel);
        panel.add(subtitleLabel);
        
        // Add new booking button
        // JButton newBookingBtn = createPrimaryButton("Make a Booking");
        // newBookingBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        // newBookingBtn.setBorder(BorderFactory.createCompoundBorder(
        //     BorderFactory.createLineBorder(ACCENT_COLOR, 1, true),
        //     BorderFactory.createEmptyBorder(8, 15, 8, 15)
        // ));
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        // buttonPanel.add(newBookingBtn);
        
        panel.add(buttonPanel);
        
        // Center everything
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.setBackground(Color.WHITE);
        wrapperPanel.add(panel);
        
        return wrapperPanel;
    }
    
    private JPanel createBookingCard(String carName, String carDetails, String licensePlate, 
                                   String dateRange, String pickup, String price, String status) {
        JPanel card = new JPanel(new BorderLayout(20, 0));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        
        // Left part - car image
        JLabel carImageLabel = new JLabel(createCarIcon());
        carImageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        card.add(carImageLabel, BorderLayout.WEST);
        
        // Center part - booking details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setOpaque(false);
        
        // Car name and status
        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.setOpaque(false);
        
        JLabel nameLabel = new JLabel(carName);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        namePanel.add(nameLabel, BorderLayout.WEST);
        
        JLabel statusLabel = createStatusLabel(status);
        namePanel.add(statusLabel, BorderLayout.EAST);
        
        detailsPanel.add(namePanel);
        detailsPanel.add(Box.createVerticalStrut(5));
        
        // Car details
        JLabel detailsLabel = new JLabel(carDetails);
        detailsLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        detailsLabel.setForeground(new Color(100, 100, 100));
        detailsPanel.add(detailsLabel);
        detailsPanel.add(Box.createVerticalStrut(5));
        
        // License plate
        JLabel plateLabel = new JLabel("License Plate: " + licensePlate);
        plateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        detailsPanel.add(plateLabel);
        detailsPanel.add(Box.createVerticalStrut(10));
        
        // Date range with icon
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        datePanel.setOpaque(false);
        
        JLabel calendarIcon = new JLabel("📅");
        JLabel dateLabel = new JLabel(dateRange);
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        datePanel.add(calendarIcon);
        datePanel.add(dateLabel);
        detailsPanel.add(datePanel);
        detailsPanel.add(Box.createVerticalStrut(5));
        
        // Pickup location with icon
        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        locationPanel.setOpaque(false);
        
        JLabel locationIcon = new JLabel("📍");
        JLabel locationLabel = new JLabel(pickup);
        locationLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        locationPanel.add(locationIcon);
        locationPanel.add(locationLabel);
        detailsPanel.add(locationPanel);
        
        card.add(detailsPanel, BorderLayout.CENTER);
        
        // Right part - price and buttons
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false);
        
        // Price
        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        priceLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rightPanel.add(priceLabel);
        rightPanel.add(Box.createVerticalStrut(10));
        
        // Action buttons
        if ("active".equals(status) || "confirmed".equals(status)) {
            JButton viewButton = createSecondaryButton("View Details");
            viewButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
            rightPanel.add(viewButton);
            rightPanel.add(Box.createVerticalStrut(10));
            
            JButton cancelButton = new JButton("Cancel");
            cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            cancelButton.setForeground(CANCELLED_COLOR);
            cancelButton.setBorderPainted(false);
            cancelButton.setContentAreaFilled(false);
            cancelButton.setFocusPainted(false);
            cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            cancelButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
            rightPanel.add(cancelButton);
        } else {
            JButton viewButton = createSecondaryButton("View Receipt");
            viewButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
            rightPanel.add(viewButton);
            rightPanel.add(Box.createVerticalStrut(10));
            
            JButton bookAgainButton = createSecondaryButton("Book Again");
            bookAgainButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
            rightPanel.add(bookAgainButton);
        }
        
        card.add(rightPanel, BorderLayout.EAST);
        
        return card;
    }
    
    private JLabel createStatusLabel(String status) {
        JLabel label = new JLabel(capitalizeFirstLetter(status));
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setOpaque(true);
        label.setBorder(BorderFactory.createEmptyBorder(3, 8, 3, 8));
        
        switch (status) {
            case "active":
                label.setBackground(ACTIVE_COLOR);
                label.setForeground(Color.WHITE);
                break;
            case "confirmed":
                label.setBackground(ACCENT_COLOR);
                label.setForeground(Color.WHITE);
                break;
            case "completed":
                label.setBackground(COMPLETED_COLOR);
                label.setForeground(Color.WHITE);
                break;
            case "cancelled":
                label.setBackground(CANCELLED_COLOR);
                label.setForeground(Color.WHITE);
                break;
            default:
                label.setBackground(new Color(200, 200, 200));
                label.setForeground(Color.BLACK);
        }
        
        return label;
    }
    
    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
    
    private JButton createPrimaryButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(ACCENT_COLOR);
        button.setForeground(Color.WHITE);
        // button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
    
    private JButton createSecondaryButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBackground(Color.WHITE);
        button.setForeground(ACCENT_COLOR);
        // button.setBorder(BorderFactory.createLineBorder(ACCENT_COLOR, 1));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
    
    private ImageIcon createEmptyStateIcon() {
        // Create a simple car icon placeholder
        int size = 100;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        
        // Enable anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw a light circle background
        g2d.setColor(new Color(240, 240, 240));
        g2d.fillOval(0, 0, size, size);
        
        // Draw a car shape
        g2d.setColor(new Color(200, 200, 200));
        g2d.fillRoundRect(size/4, size/3, size/2, size/4, 10, 10);
        g2d.fillRoundRect(size/6, size/2, size*2/3, size/4, 10, 10);
        
        // Draw wheels
        g2d.setColor(new Color(150, 150, 150));
        g2d.fillOval(size/5, size*5/8, size/6, size/6);
        g2d.fillOval(size*3/5, size*5/8, size/6, size/6);
        
        g2d.dispose();
        
        return new ImageIcon(image);
    }
    
    private ImageIcon createCarIcon() {
        // Create a car image placeholder
        int width = 120;
        int height = 80;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        
        // Enable anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw a car body
        g2d.setColor(new Color(70, 130, 200));
        
        // Main body
        g2d.fillRoundRect(width/6, height/4, width*2/3, height/3, 10, 10);
        g2d.fillRoundRect(width/8, height/2, width*3/4, height/3, 10, 10);
        
        // Windows
        g2d.setColor(new Color(220, 220, 220));
        int[] xPoints = {width/6 + 5, width/3, width/2, width*2/3 - 5};
        int[] yPoints = {height/4 + 5, height/6, height/6, height/4 + 5};
        g2d.fillPolygon(xPoints, yPoints, 4);
        
        // Wheels
        g2d.setColor(Color.BLACK);
        g2d.fillOval(width/4, height*2/3, width/6, width/6);
        g2d.fillOval(width*3/5, height*2/3, width/6, width/6);
        
        // Wheel caps
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillOval(width/4 + 5, height*2/3 + 5, width/6 - 10, width/6 - 10);
        g2d.fillOval(width*3/5 + 5, height*2/3 + 5, width/6 - 10, width/6 - 10);
        
        g2d.dispose();
        
        return new ImageIcon(image);
    }
    
    /**
     * Adds a new booking to the active bookings tab
     * @param carName The name of the car
     * @param carType The type of vehicle (e.g., "Sedan", "SUV")
     * @param transmission Transmission type (e.g., "Automatic", "Manual")
     * @param seats Number of seats (e.g., "5 Seats")
     * @param licensePlate License plate number
     * @param pickupDate Pickup date (formatted)
     * @param returnDate Return date (formatted)
     * @param pickupLocation Pickup location
     * @param price Total price
     * @return true if booking was added successfully
     */
    public boolean addNewBooking(String carName, String carType, String transmission, 
                               String seats, String licensePlate, String pickupDate, 
                               String returnDate, String pickupLocation, String price) {
        if (activeContentPanel == null) {
            return false;
        }
        
        // Format car details
        String carDetails = carType + " • " + transmission + " • " + seats;
        
        // Format date range
        String dateRange = formatDateRange(pickupDate, returnDate);
        
        // Format pickup location
        String pickup = "Pickup: " + pickupLocation;
        
        // Format price - if price doesn't start with ₱, add it
        if (!price.startsWith("₱")) {
            price = "₱" + price;
        }
        
        // If first booking, don't add spacing
        if (activeContentPanel.getComponentCount() > 0) {
            activeContentPanel.add(Box.createVerticalStrut(20));
        }
        
        // Create and add the booking card
        JPanel bookingCard = createBookingCard(
            carName,
            carDetails,
            licensePlate,
            dateRange,
            pickup,
            price,
            "confirmed"
        );
        
        activeContentPanel.add(bookingCard);
        activeContentPanel.revalidate();
        activeContentPanel.repaint();
        
        // Switch to the bookings tab
        tabbedPane.setSelectedIndex(0); // Select the active tab
        
        return true;
    }
    
    private String formatDateRange(String pickupDate, String returnDate) {
        // Extract just the date parts
        String pickupPart = pickupDate.replace(", 10:00 AM", "");
        String returnPart = returnDate.replace(", 10:00 AM", "");
        
        return pickupPart + " - " + returnPart;
    }
} 