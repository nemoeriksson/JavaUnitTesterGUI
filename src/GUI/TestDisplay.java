package GUI;

import javax.swing.*;
import java.awt.*;

public class TestDisplay extends JPanel{
    private final CardLayout layout = new CardLayout();

    private final TestDetailsPanel testDetailsPanel;
    private final MessageBox messageBox;

    private final IconSet<IconType> icons = new IconSet<>();

    public enum IconType {
        INFO, ERROR, SUCCESS
    }

    // Constructors

    public TestDisplay(StyleGuide style) {
        // Set icons
        icons.addIcon(IconType.INFO, "/resources/info-circle.png");
        icons.addIcon(IconType.SUCCESS, "/resources/check-circle.png");
        icons.addIcon(IconType.ERROR, "/resources/alert-circle.png");

        JPanel emptyPanel = new JPanel(); // Viewed by default
        messageBox = new MessageBox(style);
        testDetailsPanel = new TestDetailsPanel(icons, style);

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
