import GUI.Header;
import GUI.StyleGuide;
import GUI.TestDisplay;

import javax.swing.*;
import java.awt.*;

public class UnitTester {
    public static void main(String[] args) {
        //TODO: Make sizes dynamic

        Dimension windowSize = new Dimension(600, 400);

        JFrame window = new JFrame("Custom Unit Tester - NUnit1");
        window.setPreferredSize(new Dimension(windowSize));
        window.setLocation(200, 200);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        StyleGuide styleGuide = new StyleGuide();
        styleGuide.setMainColor(new Color(0,122,122));
        styleGuide.setWhite(new Color(242, 242, 242));

        Header header = new Header(new Dimension(windowSize.width, 48), styleGuide);
        TestDisplay mainSection = new TestDisplay(styleGuide);

        window.add(header.getPanel());
        window.add(mainSection.getPanel());
        window.pack();
        window.setVisible(true);
    }
}