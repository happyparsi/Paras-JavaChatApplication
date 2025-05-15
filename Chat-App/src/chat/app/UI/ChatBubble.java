package chat.app.UI;

import javax.swing.*;
import java.awt.*;

public class ChatBubble extends JPanel {
    private final String message;
    private final String sender;
    private final String time;
    private final boolean isCurrentUser;
    private static final int PADDING = 15;
    private static final int CORNER_RADIUS = 15;
    private static final Color CURRENT_USER_COLOR = new Color(64, 158, 255);
    private static final Color OTHER_USER_COLOR = new Color(240, 240, 240);
    private static final Color CURRENT_USER_TEXT = Color.WHITE;
    private static final Color OTHER_USER_TEXT = new Color(51, 51, 51);
    private static final Font MESSAGE_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font METADATA_FONT = new Font("Segoe UI", Font.PLAIN, 11);

    public ChatBubble(String message, String sender, String time, boolean isCurrentUser) {
        this.message = message;
        this.sender = sender;
        this.time = time;
        this.isCurrentUser = isCurrentUser;
        setOpaque(false);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Calculate dimensions
        FontMetrics messageFm = g2.getFontMetrics(MESSAGE_FONT);
        FontMetrics metadataFm = g2.getFontMetrics(METADATA_FONT);

        int messageWidth = messageFm.stringWidth(message);
        int bubbleWidth = Math.min(messageWidth + 2 * PADDING, getWidth() - 40);
        int totalHeight = messageFm.getHeight() + metadataFm.getHeight() + 2 * PADDING;

        // Draw bubble
        g2.setColor(isCurrentUser ? CURRENT_USER_COLOR : OTHER_USER_COLOR);
        if (isCurrentUser) {
            g2.fillRoundRect(getWidth() - bubbleWidth - 10, 5, bubbleWidth, totalHeight, CORNER_RADIUS, CORNER_RADIUS);
        } else {
            g2.fillRoundRect(10, 5, bubbleWidth, totalHeight, CORNER_RADIUS, CORNER_RADIUS);
        }

        // Draw message
        g2.setFont(MESSAGE_FONT);
        g2.setColor(isCurrentUser ? CURRENT_USER_TEXT : OTHER_USER_TEXT);
        int x = isCurrentUser ? getWidth() - bubbleWidth - 10 + PADDING : 10 + PADDING;
        int y = 5 + PADDING + messageFm.getAscent();
        drawWrappedText(g2, message, x, y, bubbleWidth - 2 * PADDING);

        // Draw metadata (sender and time)
        g2.setFont(METADATA_FONT);
        g2.setColor(isCurrentUser ? CURRENT_USER_TEXT : new Color(128, 128, 128));
        String metadata = sender + " • " + time;
        int metadataX = isCurrentUser ? getWidth() - bubbleWidth - 10 + PADDING : 10 + PADDING;
        int metadataY = totalHeight - PADDING + 3;
        g2.drawString(metadata, metadataX, metadataY);

        g2.dispose();
    }

    private void drawWrappedText(Graphics2D g2, String text, int x, int y, int maxWidth) {
        FontMetrics fm = g2.getFontMetrics();
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            if (fm.stringWidth(line + word) < maxWidth) {
                line.append(word).append(" ");
            } else {
                g2.drawString(line.toString(), x, y);
                y += fm.getHeight();
                line = new StringBuilder(word + " ");
            }
        }
        g2.drawString(line.toString(), x, y);
    }

    @Override
    public Dimension getPreferredSize() {
        FontMetrics messageFm = getFontMetrics(MESSAGE_FONT);
        FontMetrics metadataFm = getFontMetrics(METADATA_FONT);
        int messageWidth = messageFm.stringWidth(message);
        int bubbleWidth = Math.min(messageWidth + 2 * PADDING, 400);
        int height = messageFm.getHeight() + metadataFm.getHeight() + 2 * PADDING + 10;
        return new Dimension(bubbleWidth + 20, height);
    }
}