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

        successfullCountLabel = createLabel(style.getIcon(StyleGuide.IconType.SUCCESS), "Successful tests: ");
        failedCountLabel = createLabel(style.getIcon(StyleGuide.IconType.FAILURE), "Failed tests: ");
        erroredCountLabel = createLabel(style.getIcon(StyleGuide.IconType.ERROR), "Errored tests: ");

        add(successfullCountLabel);
        add(failedCountLabel);
        add(erroredCountLabel);
    }

    // Public methods

    // Private methods

    private JLabel createLabel(ImageIcon icon, String text) {
        JLabel label = new JLabel(text);
        label.setIcon(icon);
        label.setIconTextGap(8);
        label.setBorder(BorderFactory.createEmptyBorder(8,8,4,8));
        return label;
    }
}
