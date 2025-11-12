package GUI;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class SummaryPanel extends JPanel {
    private final BoxLayout layout;

    private final JLabel successfullCountLabel;
    private final JLabel failedCountLabel;
    private final JLabel erroredCountLabel;

    public SummaryPanel(int width, StyleGuide style) {
        super();

        layout = new BoxLayout(this, BoxLayout.Y_AXIS);

        setLayout(layout);
        setPreferredSize(new Dimension(width, getHeight()));
        setBorder(new MatteBorder(0, 2, 0, 0, style.getBorderColor()));

        successfullCountLabel = createLabel("Successful tests: ");
        failedCountLabel = createLabel("Failed tests: ");
        erroredCountLabel = createLabel("Errored tests: ");

        add(successfullCountLabel);
        add(failedCountLabel);
        add(erroredCountLabel);
    }

    // Public methods

    // Private methods

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setBorder(BorderFactory.createEmptyBorder(8,8,4,8));
        return label;
    }
}
