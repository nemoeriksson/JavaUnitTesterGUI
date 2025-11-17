package GUI;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * A summary panel displaying information
 * about the amount of successful, failed,
 * errored, and total tests.
 *
 * @author c24nen
 * @version 25.11.17
 */
public class SummaryPanel extends JPanel {
    private final BoxLayout layout;

    private final JLabel successfullCountLabel;
    private final JLabel failedCountLabel;
    private final JLabel erroredCountLabel;
    private final JLabel totalCountLabel;

    public SummaryPanel(int width, StyleGuide style) {
        super();

        // Setup
        layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);
        setPreferredSize(new Dimension(width, getHeight()));
        setBorder(new MatteBorder(0, 2, 0, 0, style.getBorderColor()));

        // Create sub-elements
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

    /**
     * Resets all labels within the summary view.
     */
    public void resetLabels() {
        successfullCountLabel.setText("Successful: —");
        failedCountLabel.setText("Failed: —");
        erroredCountLabel.setText("Errored: —");
        totalCountLabel.setText("Total tests: —");
    }

    /**
     * Sets the numbers following each label in
     * the summary view.
     *
     * @param successful Number of successful tests
     * @param failed Number of failed tests
     * @param errored Number of errored tests
     */
    public void setLabels(int successful, int failed, int errored) {
        successfullCountLabel.setText("Successful: " + successful);
        failedCountLabel.setText("Failed: " + failed);
        erroredCountLabel.setText("Errored: " + errored);
        totalCountLabel.setText("Total tests: " + (successful+failed+errored));
    }

    // Private methods

    /**
     * Creates a label with styling specific
     * to the summary view.
     *
     * @return A new label
     */
    private JLabel createLabel() {
        JLabel label = new JLabel();
        label.setBorder(BorderFactory.createEmptyBorder(8,8,4,8));
        return label;
    }
}
