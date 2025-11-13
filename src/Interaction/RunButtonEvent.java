package Interaction;

import Core.TestExecutor;
import Core.TestResult;
import GUI.Header;
import GUI.TestDisplay;
import Core.TestInfoCollection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RunButtonEvent implements ActionListener {
    private JComboBox<String> searchBar;
    private TestDisplay testDisplay;
    private TestInfoCollection tester;

    public RunButtonEvent(TestInfoCollection tester, Header header, TestDisplay testDisplay) {
        searchBar = header.getSearchBar();
        this.testDisplay = testDisplay;
        this.tester = tester;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedItem = (String)searchBar.getSelectedItem();

        if (!validateAlternative(selectedItem)) {
            // Open message box and display error message
            testDisplay.showPanel(TestDisplay.DisplayType.MESSAGE);
            displayError(selectedItem);
            return;
        }

        // Open results panel and run setup, tests, and teardown.
        testDisplay.showPanel(TestDisplay.DisplayType.RESULT);

        TestExecutor executor = new TestExecutor(tester.getTestInfo(selectedItem));

        List<TestResult> testResults = executor.doInBackground();

        for (TestResult result : testResults) {
            System.out.println(result.getMessage());
        }
    }

    // Private methods

    private boolean validateAlternative(String selectedItem) {
        for (int i = 0; i < searchBar.getItemCount(); i++) {
            if (searchBar.getItemAt(i).equals(selectedItem))
                return tester.isValidTest(selectedItem);
        }

        return false;
    }

    private void displayError(String selectedItem) {
        if (selectedItem.isEmpty()) {
            testDisplay.getMessageBox().setMessage(
                    "Empty test class name",
                    "Name of test class can not be empty. Please enter a valid test class or select one from the dropdown."
            );
        } else {
            testDisplay.getMessageBox().setMessage(
                    "Test class not found",
                    String.format("No test class named '%s' found. Please enter a valid test class or select one from the dropdown.", selectedItem)
            );
        }
    }
}