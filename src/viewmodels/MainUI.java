import javax.swing.*;
import java.awt.*;

public class MainUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel contentPanel;
    private AuthForm authForm;
    private Home homeUI;
    private NotificationsPage notificationsPage;
    private BookingsPage bookingsPage;
    private ProfilePage profilePage;
    private SettingsPage settingsPage;
    private SideNavigation sideNav;

    public MainUI() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Car Rental Service");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));
        setBackground(Color.WHITE);

        // Initialize CardLayout for smooth transitions
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(Color.WHITE);

        // Initialize auth form
        authForm = new AuthForm(this);
        mainPanel.add(authForm, "auth");

        // Initialize content panel that will hold side nav and pages
        JPanel contentWrapper = new JPanel(new BorderLayout());
        contentWrapper.setBackground(Color.WHITE);

        // Initialize side navigation
        sideNav = new SideNavigation(this);
        contentWrapper.add(sideNav, BorderLayout.WEST);

        // Initialize content area with CardLayout for pages
        contentPanel = new JPanel(new CardLayout());
        contentPanel.setBackground(Color.WHITE);

        // Initialize all pages
        homeUI = new Home();
        notificationsPage = new NotificationsPage();
        bookingsPage = new BookingsPage();
        profilePage = new ProfilePage();
        settingsPage = new SettingsPage();

        // Add pages to content panel
        contentPanel.add(homeUI, "home");
        contentPanel.add(notificationsPage, "notifications");
        contentPanel.add(bookingsPage, "bookings");
        contentPanel.add(profilePage, "profile");
        contentPanel.add(settingsPage, "settings");

        contentWrapper.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(contentWrapper, "main");

        // Show auth form first
        cardLayout.show(mainPanel, "auth");

        add(mainPanel);
    }

    public void showHome() {
        cardLayout.show(mainPanel, "main");
        showPage("home");
    }

    public void showPage(String pageName) {
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, pageName);
    }

    //pang test 
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            new MainUI().setVisible(true);
        });
    }
} 