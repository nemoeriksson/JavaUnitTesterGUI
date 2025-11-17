import Core.ClassParser;
import GUI.ContentDisplay;
import GUI.StyleGuide;
import GUI.Window;
import Interaction.RunButtonEvent;
import Core.TestInfo;
import Core.TestInfoCollection;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The main class containing the main logic
 * for the unit tester program.
 *
 * @author c24nen
 * @version 25.11.17
 */
public class UnitTester {
    /**
     * Main method. Creates the window and sets up the UI
     * to use the core functionality.
     */
    public static void main(String[] args) {
        // GUI Styling
        StyleGuide styleGuide = new StyleGuide();
        styleGuide.setMainColor(new Color(83, 130, 161));
        styleGuide.setLightGray(new Color(234,234,234));
        styleGuide.setWhite(new Color(242, 242, 242));

        // Create GUI window
        Dimension windowSize = new Dimension(600, 400);
        Window GUI = new Window("Custom Unit Tester - NUnit1", windowSize, styleGuide);

        // Read classes
        ClassParser classParser = new ClassParser();
        List<Class<?>> testClasses = classParser.getTestClasses();

        List<String> classNames = classParser.getClassNames(testClasses);
        List<TestInfo> testClassInfoList = new ArrayList<>();
        for (Class<?> testClass : testClasses) {
            testClassInfoList.add(new TestInfo(testClass));
        }

        // Set error message if no test classes are found
        if (testClasses.isEmpty()) {
            GUI.getTestDisplay().showPanel(ContentDisplay.DisplayType.MESSAGE);
            GUI.getTestDisplay().getMessageBox().setMessage(
                    "No test classes found",
                    "Unable to find any test classes at startup. "
            );
        }

        // Interaction setup
        RunButtonEvent runButtonEvent = new RunButtonEvent(
                new TestInfoCollection(testClassInfoList),
                GUI.getHeader(),
                GUI.getTestDisplay()
        );

        GUI.getHeader().setSearchBarAlternatives(classNames);
        GUI.getHeader().setRunButtonActionListener(runButtonEvent);

        GUI.setVisible(true);
    }
}