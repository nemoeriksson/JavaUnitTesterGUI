package Interaction;

import Core.TestInfo;
import GUI.Header;
import GUI.ContentDisplay;
import Core.TestInfoCollection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class containing the implementation for
 * handling the "Run" button click for the
 * unit test program.
 *
 * @author c24nen
 * @version 25.11.17
 */
public class RunButtonEvent implements ActionListener {
    private JComboBox<String> searchBar;
    private ContentDisplay contentDisplay;
    private TestInfoCollection tester;

    public RunButtonEvent(TestInfoCollection tester, Header header, ContentDisplay contentDisplay) {
        searchBar = header.getSearchBar();
        this.contentDisplay = contentDisplay;
        this.tester = tester;
    }

    // Public methods

    /**
     * Gets the selected test class from the search
     * bar and attempts to execute all test methods
     * for the selected test class.
     *
     * @param e The event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedItem = (String) searchBar.getSelectedItem();

        if (!validateAlternative(selectedItem)) {
            // Open message box and display error message
            contentDisplay.showPanel(ContentDisplay.DisplayType.MESSAGE);
            displayError(selectedItem);
            return;
        }

        TestInfo testInfo = tester.getTestInfo(selectedItem);

        // Setup GUI
        contentDisplay.showPanel(ContentDisplay.DisplayType.RESULT);
        contentDisplay.reset();
        contentDisplay.getScrollablePanel().createNewLabel(String.format(
                "Running tests for %s - please wait",
                testInfo.getTestClass().getName()
                ));

        // Run all tests
        TestExecutor executor = new TestExecutor(testInfo, contentDisplay);
        executor.execute();
    }

    // Private methods

    /**
     * Validates that the selected item is a valid test class.
     *
     * @param selectedItem Name of test class
     * @return true if selected item is a valid test class, else false
     */
    private boolean validateAlternative(String selectedItem) {
        for (int i = 0; i < searchBar.getItemCount(); i++) {
            if (searchBar.getItemAt(i).equals(selectedItem))
                return tester.isValidTest(selectedItem);
        }

        return false;
    }

    /**
     * Displays an error depending on the selected
     * item. The selected item is assumed invalid.
     *
     * @param selectedItem The selected item
     */
    private void displayError(String selectedItem) {
        if (selectedItem.isEmpty()) {
            contentDisplay.getMessageBox().setMessage(
                    "Empty test class name",
                    "Name of test class can not be empty. Please enter a valid test class or select one from the dropdown."
            );
        } else {
            contentDisplay.getMessageBox().setMessage(
                    "Test class not found",
                    String.format("No test class named '%s' found. Please enter a valid test class or select one from the dropdown.", selectedItem)
            );
        }
    }
}