package GUI;

import javax.swing.*;
import java.awt.*;

public class ContentDisplay extends JPanel{
    private final CardLayout layout = new CardLayout();

    private final ResultInfoPanel resultInfoPanel;
    private final MessageBox messageBox;

    public enum DisplayType {
        EMPTY("empty"),
        MESSAGE("msgpanel"),
        RESULT("respanel");

        private String str;

        DisplayType(String str) { this.str = str; }

        @Override
        public String toString() { return str; }
    }

    // Constructors

    public ContentDisplay(StyleGuide style) {
        // Set icons
        JPanel emptyPanel = new JPanel(); // Viewed by default
        messageBox = new MessageBox(style);
        resultInfoPanel = new ResultInfoPanel(style);

        setBackground(style.getWhite());
        setLayout(this.layout);

        add(emptyPanel, "empty");
        add(messageBox, "msgpanel");
        add(resultInfoPanel, "respanel");

        showPanel(DisplayType.EMPTY);
    }

    // Public methods

    public void showPanel(DisplayType type){
        layout.show(this, type.toString());
    }

    public MessageBox getMessageBox() { return messageBox; }
    public SummaryPanel getSummaryPanel() { return resultInfoPanel.getSummaryPanel(); }
    public ScrollablePanel getScrollablePanel() { return resultInfoPanel.getScrollablePanel(); }

    public void reset() {
        resultInfoPanel.getSummaryPanel().resetLabels();
        resultInfoPanel.getScrollablePanel().reset();
    }

    // Private methods

}
