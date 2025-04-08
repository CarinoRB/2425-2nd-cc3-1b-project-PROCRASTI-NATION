import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class AuthForm extends JPanel {
    private JPanel headerPanel, contentPanel, loginPanel, signupPanel;
    private JTextField emailField, signupEmailField;
    private JPasswordField passwordField, signupPasswordField, confirmPasswordField;
    private JButton loginTab, signupTab, loginButton, registerButton, forgotPasswordLink;
    private JLabel passwordStrengthLabel, showHidePasswordLabel;
    private MainUI mainUI;
    
    // @rayster: style stuff we can use put this in its own file pero wag na HAHHAHAHAHAHH
    private final Color BACKGROUND_COLOR = Color.WHITE;
    private final Color ACTIVE_TAB_COLOR = new Color(66, 133, 244); // Blue accent color
    private final Color INACTIVE_TAB_COLOR = new Color(150, 150, 150);
    private final Color BUTTON_COLOR = new Color(66, 133, 244);
    private final Color FIELD_BORDER_COLOR = new Color(230, 230, 230);
    private final Color TEXT_COLOR = new Color(33, 33, 33);
    private final Color HINT_TEXT_COLOR = new Color(150, 150, 150);
    private final Font HEADER_FONT = new Font("Arial", Font.BOLD, 20);
    private final Font REGULAR_FONT = new Font("Arial", Font.PLAIN, 14);
    private final Font SMALL_FONT = new Font("Arial", Font.PLAIN, 11);
    
    // Track which panel is currently active
    private boolean isLoginActive = true;
    
    public AuthForm(MainUI mainUI) {
        this.mainUI = mainUI;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(0, 0));
        setBackground(BACKGROUND_COLOR);
        
        // Create a centered container
        JPanel centeredContainer = new JPanel(new GridBagLayout());
        centeredContainer.setBackground(BACKGROUND_COLOR);
        
        // Create main container with padding
        JPanel mainContainer = new JPanel(new BorderLayout(0, 20));
        mainContainer.setBackground(BACKGROUND_COLOR);
        mainContainer.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        mainContainer.setPreferredSize(new Dimension(400, 500)); // Set preferred size for the container
        
        // Create header with tabs
        createHeaderPanel();
        mainContainer.add(headerPanel, BorderLayout.NORTH);
        
        // Create content panel that will hold both login and signup forms
        contentPanel = new JPanel(new CardLayout());
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setPreferredSize(new Dimension(300, 400)); // Set preferred size for the content panel
        
        createLoginPanel();
        createSignupPanel();
        
        contentPanel.add(loginPanel, "login");
        contentPanel.add(signupPanel, "signup");
        
        mainContainer.add(contentPanel, BorderLayout.CENTER);
        
        // Add the main container to the centered container
        centeredContainer.add(mainContainer);
        
        // Add the centered container to the main panel
        add(centeredContainer, BorderLayout.CENTER);
    }
    
    private void createHeaderPanel() {
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_COLOR);
        
        // Create tab buttons that look like text links
        JPanel tabsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        tabsPanel.setBackground(BACKGROUND_COLOR);
        
        loginTab = createTabButton("Log In", true);
        signupTab = createTabButton("Sign up", false);
        
        // Add a middle dot between tabs
        JLabel dotLabel = new JLabel(" · ");
        dotLabel.setFont(HEADER_FONT);
        dotLabel.setForeground(TEXT_COLOR);
        
        tabsPanel.add(loginTab);
        tabsPanel.add(dotLabel);
        tabsPanel.add(signupTab);
        
        headerPanel.add(tabsPanel, BorderLayout.WEST);
        
        // Add tab switching logic
        loginTab.addActionListener(e -> switchToPanel("login"));
        signupTab.addActionListener(e -> switchToPanel("signup"));
    }
    
    private JButton createTabButton(String text, boolean isActive) {
        JButton button = new JButton(text);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setFont(HEADER_FONT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setForeground(isActive ? ACTIVE_TAB_COLOR : INACTIVE_TAB_COLOR);
        return button;
    }
    
    private void switchToPanel(String panelName) {
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, panelName);
        
        // Update tab appearance
        boolean isLoginPanel = panelName.equals("login");
        loginTab.setForeground(isLoginPanel ? ACTIVE_TAB_COLOR : INACTIVE_TAB_COLOR);
        signupTab.setForeground(isLoginPanel ? INACTIVE_TAB_COLOR : ACTIVE_TAB_COLOR);
        isLoginActive = isLoginPanel;
    }

    private void createLoginPanel() {
        loginPanel = new JPanel(null); // Using absolute positioning for precise control
        loginPanel.setBackground(BACKGROUND_COLOR);
        
        // Email/Username field
        JLabel emailLabel = new JLabel("Email/Username");
        emailLabel.setFont(SMALL_FONT);
        emailLabel.setForeground(HINT_TEXT_COLOR);
        emailLabel.setBounds(0, 30, 300, 20);
        
        emailField = new JTextField("Enter your email");
        emailField.setFont(REGULAR_FONT);
        emailField.setForeground(HINT_TEXT_COLOR);
        emailField.setBounds(0, 50, 300, 30);
        emailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, FIELD_BORDER_COLOR),
            BorderFactory.createEmptyBorder(5, 0, 5, 0)
        ));
        
        // Add placeholder behavior
        addPlaceholderBehavior(emailField, "Enter your email");
        
        // Password field
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(SMALL_FONT);
        passwordLabel.setForeground(HINT_TEXT_COLOR);
        passwordLabel.setBounds(0, 100, 300, 20);
        
        passwordField = new JPasswordField("Enter Password");
        passwordField.setEchoChar((char)0); // Show placeholder text initially
        passwordField.setFont(REGULAR_FONT);
        passwordField.setForeground(HINT_TEXT_COLOR);
        passwordField.setBounds(0, 120, 300, 30);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, FIELD_BORDER_COLOR),
            BorderFactory.createEmptyBorder(5, 0, 5, 0)
        ));
        
        // Add placeholder behavior
        addPasswordPlaceholderBehavior(passwordField, "Enter Password");
        
        // Password hint and show/hide option
        passwordStrengthLabel = new JLabel("Minimum 8 chars");
        passwordStrengthLabel.setFont(SMALL_FONT);
        passwordStrengthLabel.setForeground(HINT_TEXT_COLOR);
        passwordStrengthLabel.setBounds(0, 155, 150, 20);
        
        showHidePasswordLabel = new JLabel("Hide/Show");
        showHidePasswordLabel.setFont(SMALL_FONT);
        showHidePasswordLabel.setForeground(ACTIVE_TAB_COLOR);
        showHidePasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        showHidePasswordLabel.setBounds(250, 155, 50, 20);
        
        // Add show/hide password functionality
        showHidePasswordLabel.addMouseListener(new MouseAdapter() {
            boolean passwordVisible = true;
            
            @Override
            public void mouseClicked(MouseEvent e) {
                if (passwordVisible) {
                    passwordField.setEchoChar('•');
                    showHidePasswordLabel.setText("Show");
                } else {
                    passwordField.setEchoChar((char)0);
                    showHidePasswordLabel.setText("Hide");
                }
                passwordVisible = !passwordVisible;
            }
        });
        
        // Forgot password link
        forgotPasswordLink = new JButton("Forgot Password?");
        forgotPasswordLink.setBorderPainted(false);
        forgotPasswordLink.setContentAreaFilled(false);
        forgotPasswordLink.setFocusPainted(false);
        forgotPasswordLink.setForeground(ACTIVE_TAB_COLOR);
        forgotPasswordLink.setFont(SMALL_FONT);
        forgotPasswordLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPasswordLink.setHorizontalAlignment(SwingConstants.RIGHT);
        forgotPasswordLink.setBounds(180, 190, 120, 20);
        
        forgotPasswordLink.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, "Password reset functionality would go here.")
        );
        
        // Login button - Custom rounded button
        loginButton = createStyledButton("Log In");
        loginButton.setBounds(0, 230, 300, 40);
        loginButton.addActionListener(this::handleLogin);
        
        // Add components to panel
        loginPanel.add(emailLabel);
        loginPanel.add(emailField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(passwordStrengthLabel);
        loginPanel.add(showHidePasswordLabel);
        loginPanel.add(forgotPasswordLink);
        loginPanel.add(loginButton);
    }

    private void createSignupPanel() {
        signupPanel = new JPanel(null); // Using absolute positioning
        signupPanel.setBackground(BACKGROUND_COLOR);
        
        // Email field
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(SMALL_FONT);
        emailLabel.setForeground(HINT_TEXT_COLOR);
        emailLabel.setBounds(0, 30, 300, 20);
        
        signupEmailField = new JTextField("Enter your email");
        signupEmailField.setFont(REGULAR_FONT);
        signupEmailField.setForeground(HINT_TEXT_COLOR);
        signupEmailField.setBounds(0, 50, 300, 30);
        signupEmailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, FIELD_BORDER_COLOR),
            BorderFactory.createEmptyBorder(5, 0, 5, 0)
        ));
        
        // Add placeholder behavior
        addPlaceholderBehavior(signupEmailField, "Enter your email");
        
        // Password field
        JLabel passwordLabel = new JLabel("Create Password");
        passwordLabel.setFont(SMALL_FONT);
        passwordLabel.setForeground(HINT_TEXT_COLOR);
        passwordLabel.setBounds(0, 100, 300, 20);
        
        signupPasswordField = new JPasswordField("Create Password");
        signupPasswordField.setEchoChar((char)0); // Show placeholder text initially
        signupPasswordField.setFont(REGULAR_FONT);
        signupPasswordField.setForeground(HINT_TEXT_COLOR);
        signupPasswordField.setBounds(0, 120, 300, 30);
        signupPasswordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, FIELD_BORDER_COLOR),
            BorderFactory.createEmptyBorder(5, 0, 5, 0)
        ));
        
        // Add placeholder behavior
        addPasswordPlaceholderBehavior(signupPasswordField, "Create Password");
        
        // Confirm Password field
        JLabel confirmLabel = new JLabel("Confirm Password");
        confirmLabel.setFont(SMALL_FONT);
        confirmLabel.setForeground(HINT_TEXT_COLOR);
        confirmLabel.setBounds(0, 170, 300, 20);
        
        confirmPasswordField = new JPasswordField("Confirm Password");
        confirmPasswordField.setEchoChar((char)0); // Show placeholder text initially
        confirmPasswordField.setFont(REGULAR_FONT);
        confirmPasswordField.setForeground(HINT_TEXT_COLOR);
        confirmPasswordField.setBounds(0, 190, 300, 30);
        confirmPasswordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, FIELD_BORDER_COLOR),
            BorderFactory.createEmptyBorder(5, 0, 5, 0)
        ));
        
        // Add placeholder behavior
        addPasswordPlaceholderBehavior(confirmPasswordField, "Confirm Password");
        
        // Register button
        registerButton = createStyledButton("Register");
        registerButton.setBounds(0, 250, 300, 40);
        registerButton.addActionListener(this::handleRegister);
        
        // Add components to panel
        signupPanel.add(emailLabel);
        signupPanel.add(signupEmailField);
        signupPanel.add(passwordLabel);
        signupPanel.add(signupPasswordField);
        signupPanel.add(confirmLabel);
        signupPanel.add(confirmPasswordField);
        signupPanel.add(registerButton);
    }
    
    // Helper method to create a styled button with rounded corners
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(BUTTON_COLOR);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 8, 8));
                g2.dispose();
                
                super.paintComponent(g);
            }
        };
        
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    // Helper method to add placeholder behavior to text fields
    private void addPlaceholderBehavior(JTextField field, String placeholder) {
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(TEXT_COLOR);
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(HINT_TEXT_COLOR);
                }
            }
        });
    }
    
    // Helper method to add placeholder behavior to password fields
    private void addPasswordPlaceholderBehavior(JPasswordField field, String placeholder) {
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String password = new String(field.getPassword());
                if (password.equals(placeholder)) {
                    field.setText("");
                    field.setEchoChar('•'); // Set echo char when user starts typing
                    field.setForeground(TEXT_COLOR);
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                String password = new String(field.getPassword());
                if (password.isEmpty()) {
                    field.setText(placeholder);
                    field.setEchoChar((char)0); // Remove echo char to show placeholder
                    field.setForeground(HINT_TEXT_COLOR);
                }
            }
        });
    }

    private void handleLogin(ActionEvent e) {
        String email = emailField.getText();
        String passwordStr = new String(passwordField.getPassword());
        
        // Skip validation if fields contain placeholder text
        if (email.equals("Enter your email") || passwordStr.equals("Enter Password")) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields");
            return;
        }
        
        if (email.isEmpty() || passwordStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields");
            return;
        }
        
        // Check for predefined users
        if ((email.equals("admin") && passwordStr.equals("admin")) ||
            (email.equals("user") && passwordStr.equals("user")) ||
            (email.equals("driver") && passwordStr.equals("driver"))) {
            
            // Show the home window using MainUI
            mainUI.showHome();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials!");
        }
    }

    private void handleRegister(ActionEvent e) {
        String email = signupEmailField.getText();
        String password = new String(signupPasswordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        
        // Skip validation if fields contain placeholder text
        if (email.equals("Enter your email") || 
            password.equals("Create Password") ||
            confirmPassword.equals("Confirm Password")) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields");
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!");
            return;
        }
        
        // Add actual registration logic here
        JOptionPane.showMessageDialog(this, "Registration successful!");
        
        // Switch to login panel after successful registration
        switchToPanel("login");
    }
}