package GUI;

import javax.swing.*;
import java.awt.*;

public class MessageBox extends JPanel{
    private final JLabel headerLabel;
    private final JLabel contentLabel;

    public MessageBox(StyleGuide style) {
        headerLabel = generateHeaderLabel();
        contentLabel = generateContentLabel();
        JPanel containerPanel = generateContainerPanel(style);

        containerPanel.add(headerLabel);
        containerPanel.add(contentLabel);

        setBackground(style.getWhite());
        add(containerPanel);
    }

    // Public methods

    public void setMessage(ImageIcon icon, String headerText, String contentText) {
        headerLabel.setIcon(icon);

        // uses HTML to add line wrapping
        headerLabel.setText(String.format("<html><p>%s</p></html>", headerText));
        contentLabel.setText(String.format("<html><p>%s</p></html>", contentText));
    }

    // Private methods

    private JLabel generateHeaderLabel() {
        JLabel label = new JLabel();
        label.setIconTextGap(8);
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
        panel.setBackground(style.getLightGray());
        panel.setBorder(BorderFactory.createLineBorder(style.getBorderColor(), 2));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        return panel;
    }
}
