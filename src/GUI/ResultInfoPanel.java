package GUI;

import javax.swing.*;
import java.awt.*;

public class ResultInfoPanel extends JPanel {
    private final BorderLayout layout = new BorderLayout();
    private final ScrollablePanel scrollablePanel;
    private final SummaryPanel summaryPanel;

    public ResultInfoPanel(StyleGuide style) {
        super();

        setLayout(layout);

        scrollablePanel = new ScrollablePanel();
        summaryPanel = new SummaryPanel(180, style);

        add(scrollablePanel, BorderLayout.CENTER);
        add(summaryPanel, BorderLayout.EAST);
    }

    // Public methods

    public SummaryPanel getSummaryPanel() { return summaryPanel; }
    public ScrollablePanel getScrollablePanel() { return scrollablePanel; }

    // Private methods

}
