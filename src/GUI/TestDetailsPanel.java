package GUI;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class TestDetailsPanel extends JPanel {
    private final BorderLayout layout = new BorderLayout();
    private final JPanel testDetailsPanel;
    private final JPanel summaryPanel;

    public TestDetailsPanel(StyleGuide style) {
        super();

        setLayout(layout);

        testDetailsPanel = generateTestDetailsPanel();
        summaryPanel = generateSummaryPanel(style);

        add(testDetailsPanel, BorderLayout.CENTER);
        add(summaryPanel, BorderLayout.EAST);
    }

    // Public methods

    // Private methods

    private JPanel generateTestDetailsPanel() {
        JPanel panel = new JPanel();


        return panel;
    }

    private JPanel generateSummaryPanel(StyleGuide style) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(180, getHeight()));
        panel.setBorder(new MatteBorder(0, 2, 0, 0, style.getBorderColor()));
        return panel;
    }
}
