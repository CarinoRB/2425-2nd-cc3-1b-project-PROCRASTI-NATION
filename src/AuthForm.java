import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AuthForm extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel loginPanel, signupPanel;
    private JTextField emailField, signupEmailField;
    private JPasswordField passwordField, signupPasswordField, confirmPasswordField;
    
    public AuthForm() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Car Rental Authentication");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        createLoginPanel();
        createSignupPanel();

        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Sign Up", signupPanel);

        add(tabbedPane);
    }

    private void createLoginPanel() {
        loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Email
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        emailField = new JTextField(20);
        loginPanel.add(emailField, gbc);

        // Password
        gbc.gridy = 1;
        gbc.gridx = 0;
        loginPanel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        loginPanel.add(passwordField, gbc);

        // Remember Me
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JCheckBox rememberMe = new JCheckBox("Remember me");
        loginPanel.add(rememberMe, gbc);

        // Login Button
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this::handleLogin);
        loginPanel.add(loginButton, gbc);
    }

    private void createSignupPanel() {
        signupPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Email
        gbc.gridx = 0;
        gbc.gridy = 0;
        signupPanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        signupEmailField = new JTextField(20);
        signupPanel.add(signupEmailField, gbc);

        // Password
        gbc.gridy = 1;
        gbc.gridx = 0;
        signupPanel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        signupPasswordField = new JPasswordField(20);
        signupPanel.add(signupPasswordField, gbc);

        // Confirm Password
        gbc.gridy = 2;
        gbc.gridx = 0;
        signupPanel.add(new JLabel("Confirm Password:"), gbc);

        gbc.gridx = 1;
        confirmPasswordField = new JPasswordField(20);
        signupPanel.add(confirmPasswordField, gbc);

        // Remember Me
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JCheckBox rememberMe = new JCheckBox("Remember me");
        signupPanel.add(rememberMe, gbc);

        // Register Button
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(this::handleRegister);
        signupPanel.add(registerButton, gbc);
    }

    private void handleLogin(ActionEvent e) {
        String email = emailField.getText();
        char[] password = passwordField.getPassword();
        
        if (email.isEmpty() || password.length == 0) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields");
            return;
        }
        
        // Add actual authentication logic here
        JOptionPane.showMessageDialog(this, "Login attempted");
    }

    private void handleRegister(ActionEvent e) {
        String email = signupEmailField.getText();
        char[] password = signupPasswordField.getPassword();
        char[] confirmPassword = confirmPasswordField.getPassword();

        if (!new String(password).equals(new String(confirmPassword))) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!");
            return;
        }
        
        // Add actual registration logic here
        JOptionPane.showMessageDialog(this, "Registration successful!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AuthForm().setVisible(true));
    }
}
