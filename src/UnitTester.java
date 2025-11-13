import GUI.StyleGuide;
import GUI.Window;
import Intermediate.RunButtonEvent;
import Tester.TestClassInfo;
import Tester.TestRunner;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UnitTester {
    public static void main(String[] args) {
        // GUI Styling
        StyleGuide styleGuide = new StyleGuide();
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

        // Setup
        GUI.getHeader().setSearchBarAlternatives(classNames);
        GUI.getHeader().setRunButtonActionListener(new RunButtonEvent(GUI.getHeader(), GUI.getTestDisplay()));

        List<TestClassInfo> testClassInfoList = new ArrayList<>();
        for (Class<?> testClass : testClasses) {
            testClassInfoList.add(new TestClassInfo(testClass));
        }

        TestRunner tester = new TestRunner();

        GUI.setVisible(true);
    }
}