package GUI;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class TestDetailsPanel extends JPanel {
    private final BorderLayout layout = new BorderLayout();
    private final JPanel testDetailsPanel;
    private final SummaryPanel summaryPanel;

    public TestDetailsPanel(IconSet<TestDisplay.IconType> icons, StyleGuide style) {
        super();

        setLayout(layout);

        testDetailsPanel = generateTestDetailsPanel();
        summaryPanel = new SummaryPanel(180, icons, style);

        add(testDetailsPanel, BorderLayout.CENTER);
        add(summaryPanel, BorderLayout.EAST);
    }

    // Public methods

    // Private methods

    private JPanel generateTestDetailsPanel() {
        JPanel panel = new JPanel();


        return panel;
    }
}
