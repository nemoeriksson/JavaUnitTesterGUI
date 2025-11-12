package GUI;

import javax.swing.*;
import java.awt.*;

public class TestDisplay extends JPanel{
    private final CardLayout layout = new CardLayout();

    private final TestDetailsPanel testDetailsPanel;
    private final MessageBox messageBox;

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

        layout.show(this, "respanel");
    }

    // Public methods

    // Private methods

}
