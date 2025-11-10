package GUI;

import javax.swing.*;
import java.awt.*;

public class TestDisplay {
    private final JPanel mainPanel;
    private final CardLayout layout;

    private final TestDetailsPanel testDetailsPanel;
    private final MessageBox messageBox;
    private final StyleGuide style;

    private final IconSet<IconType> icons = new IconSet<>();

    public enum IconType {
        INFO, ERROR, SUCCESS
    }

    // Constructors

    public TestDisplay(StyleGuide style) {
        this.style = style;
        layout = new CardLayout();

        // Set icons
        icons.addIcon(IconType.INFO, "/resources/info-circle.png");
        icons.addIcon(IconType.SUCCESS, "/resources/check-circle.png");
        icons.addIcon(IconType.ERROR, "/resources/alert-circle.png");

        messageBox = new MessageBox(style);
        testDetailsPanel = new TestDetailsPanel(icons, style);

        mainPanel = generateMainPanel();

        JPanel emptyPanel = new JPanel();
        mainPanel.add(emptyPanel, "empty");

        mainPanel.add(messageBox.getPanel(), "msgpanel");
        mainPanel.add(testDetailsPanel, "respanel");

        layout.show(mainPanel, "respanel");
    }

    // Public methods

    public JPanel getPanel() { return mainPanel; }

    // Private methods

    private JPanel generateMainPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(style.getWhite());
        panel.setLayout(this.layout);

        return panel;
    }
}
