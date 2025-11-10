import GUI.StyleGuide;
import GUI.Window;

import java.awt.*;

public class UnitTester {
    public static void main(String[] args) {
        //TODO: Make sizes dynamic

        Dimension windowSize = new Dimension(600, 400);

        StyleGuide styleGuide = new StyleGuide();
        styleGuide.setMainColor(new Color(0,122,122));
        styleGuide.setWhite(new Color(242, 242, 242));
        styleGuide.setLightGray(new Color(234,234,234));

        Window window = new Window("Custom Unit Tester - NUnit1", windowSize, styleGuide);

        window.setVisible(true);
    }
}