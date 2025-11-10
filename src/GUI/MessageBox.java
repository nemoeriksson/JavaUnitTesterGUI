package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MessageBox {
    private final JPanel mainPanel;
    private final JLabel headerLabel;
    private final JLabel contentLabel;

    private final ImageIcon infoIcon;
    private final ImageIcon errorIcon;
    private final ImageIcon successIcon;

    public enum MessageType {
        INFO, ERROR, SUCCESS;
    }

    public MessageBox(StyleGuide style) {
        infoIcon = getImageIcon("/resources/info-circle.png");
        errorIcon = getImageIcon("/resources/alert-circle.png");
        successIcon = getImageIcon("/resources/check-circle.png");

        headerLabel = generateHeaderLabel();
        contentLabel = generateContentLabel();
        JPanel containerPanel = generateContainerPanel(style);

        containerPanel.add(headerLabel);
        containerPanel.add(contentLabel);

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.add(containerPanel, new GridBagConstraints());
    }

    // Public methods

    public JPanel getPanel() { return mainPanel; }

    public void setMessage(MessageType type, String headerText, String contentText) {
        switch (type) {
            case INFO -> headerLabel.setIcon(infoIcon);
            case ERROR -> headerLabel.setIcon(errorIcon);
            case SUCCESS -> headerLabel.setIcon(successIcon);
        }

        // HTML to add line wrapping easily
        headerLabel.setText(String.format("<html><p>%s</p></html>", headerText));
        contentLabel.setText(String.format("<html><p>%s</p></html>", contentText));
    }

    // Private methods

    private ImageIcon getImageIcon(String resourcePath) {
        URL url = this.getClass().getResource(resourcePath);
        return new ImageIcon(Toolkit.getDefaultToolkit().getImage(url));
    }

    private JLabel generateHeaderLabel() {
        JLabel label = new JLabel();
        label.setIcon(infoIcon);
        label.setIconTextGap(10);
        label.setBorder(BorderFactory.createEmptyBorder(4,12,0,12));

        return label;
    }

    private JLabel generateContentLabel() {
        JLabel label = new JLabel();
        label.setBorder(BorderFactory.createEmptyBorder(12,12,4,12));

        return label;
    }

    private JPanel generateContainerPanel(StyleGuide style) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(225, 144));
        panel.setBackground(style.getWhite());
        panel.setBorder(BorderFactory.createLineBorder(style.getBorderColor(), 2));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        return panel;
    }
}
