package GUI;

import javax.swing.*;
import java.awt.*;

public class TestDisplay extends JPanel{
    private final CardLayout layout = new CardLayout();

    private final TestDetailsPanel testDetailsPanel;
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

    public TestDisplay(StyleGuide style) {
        // Set icons
        JPanel emptyPanel = new JPanel(); // Viewed by default
        messageBox = new MessageBox(style);
        testDetailsPanel = new TestDetailsPanel(style);

        setBackground(style.getWhite());
        setLayout(this.layout);

        add(emptyPanel, "empty");
        add(messageBox, "msgpanel");
        add(testDetailsPanel, "respanel");

        showPanel(DisplayType.EMPTY);
    }

    // Public methods

    public void showPanel(DisplayType type){
        layout.show(this, type.toString());
    }

    public MessageBox getMessageBox() { return messageBox; }

    // Private methods

}
