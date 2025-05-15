// Source code is decompiled from a .class file using FernFlower decompiler.
package chat.app.UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private static final Color BACKGROUND_COLOR = new Color(255, 255, 255);
    private static final Color PRIMARY_COLOR = new Color(64, 158, 255);
    private static final Color TEXT_COLOR = new Color(51, 51, 51);
    private static final Color BORDER_COLOR = new Color(230, 230, 230);

    public LoginFrame() {
        super("Chat Application - Login");
        this.setupUI();
    }

    private void setupUI() {
        this.setDefaultCloseOperation(3);
        this.setMinimumSize(new Dimension(400, 500));
        this.setBackground(BACKGROUND_COLOR);
        JPanel var1 = new JPanel();
        var1.setLayout(new BoxLayout(var1, 1));
        var1.setBackground(BACKGROUND_COLOR);
        var1.setBorder(new EmptyBorder(40, 40, 40, 40));
        JLabel var2 = new JLabel("Welcome Back!");
        var2.setFont(new Font("Segoe UI", 1, 24));
        var2.setForeground(TEXT_COLOR);
        var2.setAlignmentX(0.5F);
        JLabel var3 = new JLabel("Please enter your username to continue");
        var3.setFont(new Font("Segoe UI", 0, 14));
        var3.setForeground(new Color(128, 128, 128));
        var3.setAlignmentX(0.5F);
        this.usernameField = new JTextField();
        this.usernameField.setFont(new Font("Segoe UI", 0, 14));
        this.usernameField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(BORDER_COLOR), new EmptyBorder(10, 12, 10, 12)));
        this.usernameField.setMaximumSize(new Dimension(300, 40));
        ModernButton var4 = new ModernButton("Login");
        var4.setMaximumSize(new Dimension(300, 40));
        var4.setAlignmentX(0.5F);
        var1.add(Box.createVerticalGlue());
        var1.add(var2);
        var1.add(Box.createRigidArea(new Dimension(0, 10)));
        var1.add(var3);
        var1.add(Box.createRigidArea(new Dimension(0, 30)));
        var1.add(this.usernameField);
        var1.add(Box.createRigidArea(new Dimension(0, 20)));
        var1.add(var4);
        var1.add(Box.createVerticalGlue());
        this.setContentPane(var1);
        this.pack();
        this.setLocationRelativeTo((Component)null);
    }

    public String getUsername() {
        return this.usernameField.getText().trim();
    }

    public void setLoginButtonAction(Runnable var1) {
        Component[] var2 = this.getContentPane().getComponents();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Component var5 = var2[var4];
            if (var5 instanceof ModernButton) {
                ((ModernButton)var5).addActionListener((var1x) -> {
                    var1.run();
                });
            }
        }

    }
}
