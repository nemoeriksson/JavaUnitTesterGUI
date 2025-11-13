package GUI;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class SummaryPanel extends JPanel {
    private final BoxLayout layout;

    private final JLabel successfullCountLabel;
    private final JLabel failedCountLabel;
    private final JLabel erroredCountLabel;
    private final JLabel totalCountLabel;

    public SummaryPanel(int width, StyleGuide style) {
        super();

        layout = new BoxLayout(this, BoxLayout.Y_AXIS);

        setLayout(layout);
        setPreferredSize(new Dimension(width, getHeight()));
        setBorder(new MatteBorder(0, 2, 0, 0, style.getBorderColor()));

        successfullCountLabel = createLabel();
        failedCountLabel = createLabel();
        erroredCountLabel = createLabel();
        totalCountLabel = createLabel();

        resetLabels();

        add(successfullCountLabel);
        add(failedCountLabel);
        add(erroredCountLabel);
        add(totalCountLabel);
    }

    // Public methods

    public void resetLabels() {
        successfullCountLabel.setText("Successful: —");
        failedCountLabel.setText("Failed: —");
        erroredCountLabel.setText("Errored: —");
        totalCountLabel.setText("Total tests: —");
    }

    public void setLabels(int successful, int failed, int errored) {
        successfullCountLabel.setText("Successful: " + successful);
        failedCountLabel.setText("Failed: " + failed);
        erroredCountLabel.setText("Errored: " + errored);
        totalCountLabel.setText("Total tests: " + (successful+failed+errored));
    }

    // Private methods

    private JLabel createLabel() {
        JLabel label = new JLabel();
        label.setBorder(BorderFactory.createEmptyBorder(8,8,4,8));
        return label;
    }
}
