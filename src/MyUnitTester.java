import GUI.Header;

import javax.swing.*;
import java.awt.*;

public class MyUnitTester {
    public static void main(String[] args) {
        //TODO: Make sizes dynamic

        Dimension windowSize = new Dimension(600, 400);
        FlowLayout nonGapFlowLayout = new FlowLayout(FlowLayout.CENTER);
        nonGapFlowLayout.setHgap(0);
        nonGapFlowLayout.setVgap(0);

        JFrame window = new JFrame("Custom Unit Tester - NUnit1");
        window.setPreferredSize(new Dimension(windowSize));
        window.setLocation(200, 200);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Header header = new Header(new Dimension(windowSize.width, 48));

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(222,222,222));

        CardLayout cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        JPanel messagePanel = new JPanel(new GridBagLayout());

        JPanel message = new JPanel();
        message.setPreferredSize(new Dimension(200, 120));
        message.setBackground(new Color(215,215,215));

        messagePanel.add(message, new GridBagConstraints());

        JPanel resultsPanel = new JPanel();
        resultsPanel.setBackground(new Color(0,0,255));

        mainPanel.add(messagePanel, "msgpanel");
        mainPanel.add(resultsPanel, "respanel");

        window.add(header.getPanel());
        window.add(mainPanel);
        window.pack();
        window.setVisible(true);
    }
}