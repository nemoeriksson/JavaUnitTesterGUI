package GUI;

import javax.swing.*;
import java.awt.*;

public class TestDisplay {
    private final JPanel mainPanel;
    private final CardLayout layout;
    private final JPanel resultsPanel;

    private final MessageBox messageBox;

    private final StyleGuide style;

    // Constructors

    public TestDisplay(StyleGuide style) {
        this.style = style;

        layout = new CardLayout();

        messageBox = new MessageBox(style);
        resultsPanel = generateResultsPanel();

        mainPanel = generateMainPanel();

        mainPanel.add(messageBox.getPanel(), "msgpanel");
        mainPanel.add(resultsPanel, "respanel");
    }

    // Public methods

    public JPanel getPanel() { return mainPanel; }
    public MessageBox getMessageBox() { return messageBox; }

    // Private methods

    private JPanel generateResultsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0,0,255));

        return panel;
    }

    private JPanel generateMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(this.layout);
        panel.setBackground(style.getWhite());

        return panel;
    }
}
