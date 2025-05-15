package chat.app.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import java.sql.SQLException;
import java.util.List;
import chat.app.Database.DBManager;
import chat.app.Database.UserDB;

public class AuthFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel loginPanel;
    private JPanel registerPanel;
    private CardLayout cardLayout;

    private JTextField loginUsernameField;
    private JTextField registerUsernameField;
    private JPasswordField registerPasswordField;
    private JPasswordField confirmPasswordField;
    private JTextField emailField;

    private static final Color BACKGROUND_COLOR = new Color(255, 255, 255);
    private static final Color PRIMARY_COLOR = new Color(64, 158, 255);
    private static final Color TEXT_COLOR = new Color(51, 51, 51);
    private static final Color BORDER_COLOR = new Color(230, 230, 230);
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font INPUT_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    public AuthFrame() {
        super("Chat Application - Authentication");
        setupUI();
    }

    private void setupUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 600));
        setBackground(BACKGROUND_COLOR);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(BACKGROUND_COLOR);

        setupLoginPanel();
        setupRegisterPanel();

        mainPanel.add(loginPanel, "login");
        mainPanel.add(registerPanel, "register");

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private void setupLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBackground(BACKGROUND_COLOR);
        loginPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

        // Title
        JLabel titleLabel = new JLabel("Welcome Back!");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Please login to continue");
        subtitleLabel.setFont(SUBTITLE_FONT);
        subtitleLabel.setForeground(new Color(128, 128, 128));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Username field
        loginUsernameField = createStyledTextField("Username");

        // Login button
        ModernButton loginButton = new ModernButton("Login");
        loginButton.setMaximumSize(new Dimension(300, 40));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Register link
        JPanel linkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linkPanel.setBackground(BACKGROUND_COLOR);
        JLabel registerLink = new JLabel("Don't have an account? Register");
        registerLink.setForeground(PRIMARY_COLOR);
        registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(mainPanel, "register");
            }
        });
        linkPanel.add(registerLink);

        // Add components
        loginPanel.add(Box.createVerticalGlue());
        loginPanel.add(titleLabel);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loginPanel.add(subtitleLabel);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        loginPanel.add(loginUsernameField);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        loginPanel.add(loginButton);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        loginPanel.add(linkPanel);
        loginPanel.add(Box.createVerticalGlue());
    }

    private void setupRegisterPanel() {
        registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
        registerPanel.setBackground(BACKGROUND_COLOR);
        registerPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

        // Title
        JLabel titleLabel = new JLabel("Create Account");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Please fill in your details");
        subtitleLabel.setFont(SUBTITLE_FONT);
        subtitleLabel.setForeground(new Color(128, 128, 128));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Form fields
        registerUsernameField = createStyledTextField("Username");
        emailField = createStyledTextField("Email");
        registerPasswordField = createStyledPasswordField("Password");
        confirmPasswordField = createStyledPasswordField("Confirm Password");

        // Register button
        ModernButton registerButton = new ModernButton("Register");
        registerButton.setMaximumSize(new Dimension(300, 40));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Login link
        JPanel linkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linkPanel.setBackground(BACKGROUND_COLOR);
        JLabel loginLink = new JLabel("Already have an account? Login");
        loginLink.setForeground(PRIMARY_COLOR);
        loginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(mainPanel, "login");
            }
        });
        linkPanel.add(loginLink);

        // Add components
        registerPanel.add(Box.createVerticalGlue());
        registerPanel.add(titleLabel);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        registerPanel.add(subtitleLabel);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        registerPanel.add(registerUsernameField);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        registerPanel.add(emailField);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        registerPanel.add(registerPasswordField);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        registerPanel.add(confirmPasswordField);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        registerPanel.add(registerButton);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        registerPanel.add(linkPanel);
        registerPanel.add(Box.createVerticalGlue());
    }

    private JTextField createStyledTextField(String placeholder) {
        JTextField field = new JTextField();
        field.setFont(INPUT_FONT);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR),
                new EmptyBorder(10, 12, 10, 12)
        ));
        field.setMaximumSize(new Dimension(300, 40));
        field.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add placeholder
        field.setText(placeholder);
        field.setForeground(Color.GRAY);

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
                    field.setForeground(Color.GRAY);
                }
            }
        });

        return field;
    }

    private JPasswordField createStyledPasswordField(String placeholder) {
        JPasswordField field = new JPasswordField();
        field.setFont(INPUT_FONT);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR),
                new EmptyBorder(10, 12, 10, 12)
        ));
        field.setMaximumSize(new Dimension(300, 40));
        field.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add placeholder
        field.setEchoChar((char)0);
        field.setText(placeholder);
        field.setForeground(Color.GRAY);

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(field.getPassword()).equals(placeholder)) {
                    field.setText("");
                    field.setEchoChar('•');
                    field.setForeground(TEXT_COLOR);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getPassword().length == 0) {
                    field.setEchoChar((char)0);
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });

        return field;
    }

    public void setLoginAction(Runnable action) {
        for (Component comp : loginPanel.getComponents()) {
            if (comp instanceof ModernButton) {
                ((ModernButton) comp).addActionListener(e -> {
                    String username = loginUsernameField.getText();
                    if (!username.equals("Username") && !username.isEmpty()) {
                        action.run();
                    }
                });
            }
        }
    }

    public void setRegisterAction(Runnable action) {
        for (Component comp : registerPanel.getComponents()) {
            if (comp instanceof ModernButton) {
                ((ModernButton) comp).addActionListener(e -> {
                    String username = registerUsernameField.getText();
                    String email = emailField.getText();
                    String password = String.valueOf(registerPasswordField.getPassword());
                    String confirmPassword = String.valueOf(confirmPasswordField.getPassword());

                    if (!username.equals("Username") && !username.isEmpty() &&
                            !email.equals("Email") && !email.isEmpty() &&
                            !password.equals("Password") && !password.isEmpty() &&
                            password.equals(confirmPassword)) {
                        action.run();
                    }
                });
            }
        }
    }

    public String getLoginUsername() {
        String username = loginUsernameField.getText();
        return username.equals("Username") ? "" : username;
    }

    public String getRegisterUsername() {
        String username = registerUsernameField.getText();
        return username.equals("Username") ? "" : username;
    }

    public String getEmail() {
        String email = emailField.getText();
        return email.equals("Email") ? "" : email;
    }

    public String getPassword() {
        String password = String.valueOf(registerPasswordField.getPassword());
        return password.equals("Password") ? "" : password;
    }

    public void showLoginPanel() {
        cardLayout.show(mainPanel, "login");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Set system look and feel
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Connect to database
            DBManager.ConnectDB();

            // Create authentication window
            AuthFrame authFrame = new AuthFrame();

            // Set up login action
            authFrame.setLoginAction(() -> {
                String username = authFrame.getLoginUsername();
                String password = authFrame.getPassword();
                if (DBManager.loginUser(username, password)) {
                    authFrame.dispose();

                    // Create main chat window
                    ModernChatFrame chatFrame = new ModernChatFrame("Chat Application - " + username);
                    chatFrame.setVisible(true);

                    // Add online users
                    List<UserDB> users = DBManager.GetUserList();
                    for (UserDB user : users) {
                        chatFrame.addUser(user.getUserName(), true);
                    }
                } else {
                    JOptionPane.showMessageDialog(authFrame,
                            "Invalid username or password",
                            "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            });

            // Set up register action
            authFrame.setRegisterAction(() -> {
                String username = authFrame.getRegisterUsername();
                String email = authFrame.getEmail();
                String password = authFrame.getPassword();

                if (DBManager.registerUser(username, email, password)) {
                    JOptionPane.showMessageDialog(authFrame,
                            "Registration successful! Please login.",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    authFrame.showLoginPanel();
                } else {
                    JOptionPane.showMessageDialog(authFrame,
                            "Username or email already exists",
                            "Registration Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            });

            authFrame.setVisible(true);
        });
    }
}