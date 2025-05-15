package chat.app.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModernButton extends JButton {
    private Color backgroundColor = new Color(64, 158, 255);
    private Color hoverColor = new Color(58, 142, 230);
    private Color textColor = Color.WHITE;
    private boolean isHovered = false;

    public ModernButton(String text) {
        super(text);
        setupButton();
    }

    private void setupButton() {
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setForeground(textColor);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw background
        g2.setColor(isHovered ? hoverColor : backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        // Draw text
        FontMetrics metrics = g2.getFontMetrics(getFont());
        int x = (getWidth() - metrics.stringWidth(getText())) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g2.setColor(textColor);
        g2.drawString(getText(), x, y);

        g2.dispose();
    }
}