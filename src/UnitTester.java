import GUI.StyleGuide;
import GUI.Window;

import java.awt.*;
import java.util.List;

public class UnitTester {
    public static void main(String[] args) {
        // GUI Styling
        StyleGuide styleGuide = new StyleGuide();
        styleGuide.setIcon(StyleGuide.IconType.INFO, "/resources/infoIcon.png");
        styleGuide.setIcon(StyleGuide.IconType.SUCCESS, "/resources/successIcon.png");
        styleGuide.setIcon(StyleGuide.IconType.FAILURE, "/resources/failureIcon.png");
        styleGuide.setIcon(StyleGuide.IconType.ERROR, "/resources/errorIcon.png");
        styleGuide.setMainColor(new Color(0,122,122));
        styleGuide.setLightGray(new Color(234,234,234));
        styleGuide.setWhite(new Color(242, 242, 242));

        // Create GUI window
        Dimension windowSize = new Dimension(600, 400);
        Window GUI = new Window("Custom Unit Tester - NUnit1", windowSize, styleGuide);

        // Read classes
        ClassParser classParser = new ClassParser();
        List<Class<?>> testClasses = classParser.getTestClasses();

        List<String> classNames = classParser.getClassNames(testClasses);
        GUI.getHeader().setSearchBarAlternatives(classNames);

        GUI.setVisible(true);
    }
}