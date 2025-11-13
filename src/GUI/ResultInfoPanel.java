package GUI;

import javax.swing.*;
import java.awt.*;

public class ResultInfoPanel extends JPanel {
    private final BorderLayout layout = new BorderLayout();
    private final JPanel testDetailsPanel;
    private final SummaryPanel summaryPanel;

    public ResultInfoPanel(StyleGuide style) {
        super();

        setLayout(layout);

        testDetailsPanel = generateTestDetailsPanel();
        summaryPanel = new SummaryPanel(180, style);

        add(testDetailsPanel, BorderLayout.CENTER);
        add(summaryPanel, BorderLayout.EAST);
    }

    // Public methods

    public SummaryPanel getSummaryPanel() { return summaryPanel; }

    // Private methods

    private JPanel generateTestDetailsPanel() {
        JPanel panel = new JPanel();


        return panel;
    }
}
