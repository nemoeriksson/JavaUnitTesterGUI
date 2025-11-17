package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * A class for the content display. Has three
 * different view modes.
 *
 * @author c24nen
 * @version 25.11.17
 */
public class ContentDisplay extends JPanel{
    private final CardLayout layout = new CardLayout();

    private final ResultInfoPanel resultInfoPanel;
    private final MessageBox messageBox;

    /**
     * Enum with the different view modes
     */
    public enum DisplayType {
        EMPTY("empty"),
        MESSAGE("msgpanel"),
        RESULT("respanel");

        private String str;

        DisplayType(String str) { this.str = str; }

        @Override
        public String toString() { return str; }
    }

    public ContentDisplay(StyleGuide style) {
        // Setup
        setBackground(style.getWhite());
        setLayout(this.layout);

        // Create sub-panels
        JPanel emptyPanel = new JPanel(); // Viewed by default
        messageBox = new MessageBox(style);
        resultInfoPanel = new ResultInfoPanel(style);

        add(emptyPanel, "empty");
        add(messageBox, "msgpanel");
        add(resultInfoPanel, "respanel");

        showPanel(DisplayType.EMPTY);
    }

    // Public methods

    /**
     * Sets the view mode to the selected mode.
     *
     * @param type New view mode
     */
    public void showPanel(DisplayType type){
        layout.show(this, type.toString());
    }

    /**
     * Gets the message box element.
     *
     * @return The message box
     */
    public MessageBox getMessageBox() { return messageBox; }

    /**
     * Gets the summary panel element.
     *
     * @return The summary panel.
     */
    public SummaryPanel getSummaryPanel() { return resultInfoPanel.getSummaryPanel(); }

    /**
     * Gets the scrollable panel element.
     *
     * @return The scrollable panel.
     */
    public ScrollablePanel getScrollablePanel() { return resultInfoPanel.getScrollablePanel(); }

    /**
     * Resets all output on the summary panel
     * and scrollable pane.
     */
    public void reset() {
        resultInfoPanel.getSummaryPanel().resetLabels();
        resultInfoPanel.getScrollablePanel().reset();
    }
}
