package chat.app.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class ModernChatFrame extends JFrame {
    private JPanel chatPanel;
    private JPanel messagePanel;
    private JTextField messageField;
    private JPanel userListPanel;
    private JPanel sidePanel;
    private static final Color BACKGROUND_COLOR = new Color(255, 255, 255);
    private static final Color SIDEBAR_COLOR = new Color(247, 247, 247);
    private static final Color BORDER_COLOR = new Color(230, 230, 230);

    public ModernChatFrame(String title) {
        super(title);
        setupUI();
    }

    private void setupUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(900, 600));

        // Main content panel with border layout
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(BACKGROUND_COLOR);

        // Create side panel
        setupSidePanel();

        // Create chat panel
        setupChatPanel();

        // Add panels to content
        contentPanel.add(sidePanel, BorderLayout.WEST);
        contentPanel.add(chatPanel, BorderLayout.CENTER);

        setContentPane(contentPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private void setupSidePanel() {
        sidePanel = new JPanel(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension(250, 0));
        sidePanel.setBackground(SIDEBAR_COLOR);
        sidePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, BORDER_COLOR));

        // User profile panel
        JPanel profilePanel = new JPanel(new BorderLayout());
        profilePanel.setBackground(SIDEBAR_COLOR);
        profilePanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel userLabel = new JLabel("Your Username");
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        profilePanel.add(userLabel, BorderLayout.CENTER);

        ModernButton statusButton = new ModernButton("Online");
        statusButton.setPreferredSize(new Dimension(80, 30));
        profilePanel.add(statusButton, BorderLayout.EAST);

        // User list panel
        userListPanel = new JPanel();
        userListPanel.setLayout(new BoxLayout(userListPanel, BoxLayout.Y_AXIS));
        userListPanel.setBackground(SIDEBAR_COLOR);

        JScrollPane userScrollPane = new JScrollPane(userListPanel);
        userScrollPane.setBorder(null);
        userScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        sidePanel.add(profilePanel, BorderLayout.NORTH);
        sidePanel.add(userScrollPane, BorderLayout.CENTER);
    }

    private void setupChatPanel() {
        chatPanel = new JPanel(new BorderLayout());
        chatPanel.setBackground(BACKGROUND_COLOR);

        // Messages panel
        messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        messagePanel.setBackground(BACKGROUND_COLOR);

        JScrollPane scrollPane = new JScrollPane(messagePanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Message input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(BACKGROUND_COLOR);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER_COLOR),
                new EmptyBorder(15, 15, 15, 15)
        ));

        messageField = new JTextField();
        messageField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messageField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR),
                new EmptyBorder(8, 10, 8, 10)
        ));

        ModernButton sendButton = new ModernButton("Send");
        sendButton.setPreferredSize(new Dimension(100, 40));

        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        chatPanel.add(scrollPane, BorderLayout.CENTER);
        chatPanel.add(inputPanel, BorderLayout.SOUTH);
    }

    public void addMessage(String message, String sender, String time, boolean isCurrentUser) {
        ChatBubble bubble = new ChatBubble(message, sender, time, isCurrentUser);
        messagePanel.add(bubble);
        messagePanel.revalidate();
        messagePanel.repaint();
    }

    public void addUser(String username, boolean isOnline) {
        JPanel userPanel = new JPanel(new BorderLayout());
        userPanel.setMaximumSize(new Dimension(250, 60));
        userPanel.setBackground(SIDEBAR_COLOR);
        userPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR),
                new EmptyBorder(10, 15, 10, 15)
        ));

        JLabel nameLabel = new JLabel(username);
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel statusLabel = new JLabel(isOnline ? "Online" : "Offline");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setForeground(isOnline ? new Color(76, 175, 80) : new Color(158, 158, 158));

        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        labelPanel.setBackground(SIDEBAR_COLOR);
        labelPanel.add(nameLabel);
        labelPanel.add(statusLabel);

        userPanel.add(labelPanel, BorderLayout.CENTER);
        userListPanel.add(userPanel);
        userListPanel.revalidate();
        userListPanel.repaint();
    }
}