package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Class representing a message box to
 * display different types of messages
 * in a UI.
 *
 * @author c24nen
 * @version 25.11.17
 */
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

    /**
     * Sets the message box's header and content text.
     *
     * @param headerText New header text
     * @param contentText New content text
     */
    public void setMessage(String headerText, String contentText) {
        // uses HTML to add line wrapping
        headerLabel.setText(String.format("<html><p>%s</p></html>", headerText));
        contentLabel.setText(String.format("<html><p>%s</p></html>", contentText));
    }

    // Private methods

    /**
     * Generates the header label element.
     *
     * @return New label
     */
    private JLabel generateHeaderLabel() {
        JLabel label = new JLabel();
        label.setIconTextGap(8);
        label.setBorder(BorderFactory.createEmptyBorder(4,12,0,12));
        label.setFont(new Font(label.getFont().getFontName(), label.getFont().getStyle(), 16));
        return label;
    }

    /**
     * Generates the content label element
     *
     * @return New label
     */
    private JLabel generateContentLabel() {
        JLabel label = new JLabel();
        label.setBorder(BorderFactory.createEmptyBorder(12,12,4,12));

        return label;
    }

    /**
     * Generates a container panel according
     * to the UI's style guide.
     *
     * @param style The style guide
     * @return New panel
     */
    private JPanel generateContainerPanel(StyleGuide style) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(225, 144));
        panel.setBackground(style.getLightGray());
        panel.setBorder(BorderFactory.createLineBorder(style.getBorderColor(), 2));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        return panel;
    }
}
