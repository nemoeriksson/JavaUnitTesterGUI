package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * A class for the test results view. Has
 * a summary view and scrollable panel.
 *
 * @author c24nen
 * @version 25.11.17
 */
public class ResultInfoPanel extends JPanel {
    private final BorderLayout layout = new BorderLayout();
    private final ScrollablePanel scrollablePanel;
    private final SummaryPanel summaryPanel;

    public ResultInfoPanel(StyleGuide style) {
        super();
        setLayout(layout);

        // Create sub-panels
        scrollablePanel = new ScrollablePanel();
        summaryPanel = new SummaryPanel(180, style);

        add(scrollablePanel, BorderLayout.CENTER);
        add(summaryPanel, BorderLayout.EAST);
    }

    // Public methods

    /**
     * Gets the summary panel.
     * @return Summary panel
     */
    public SummaryPanel getSummaryPanel() { return summaryPanel; }

    /**
     * Gest the scrollable panel.
     * @return Scrollable panel
     */
    public ScrollablePanel getScrollablePanel() { return scrollablePanel; }
}
