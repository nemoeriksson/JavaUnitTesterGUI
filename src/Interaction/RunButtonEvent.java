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
        String selectedItem = (String) searchBar.getSelectedItem();

        if (!validateAlternative(selectedItem)) {
            // Open message box and display error message
            testDisplay.showPanel(TestDisplay.DisplayType.MESSAGE);
            displayError(selectedItem);
            return;
        }

        // Run all tests and get results
        TestExecutor executor = new TestExecutor(tester.getTestInfo(selectedItem));

        testDisplay.reset();

        List<TestResult> testResults = executor.doInBackground();

        int successfull = 0;
        int failed = 0;
        int errored = 0;

        boolean encounteredInternalError = false;

        for (TestResult result : testResults) {
            switch (result.getStatus()) {
                case SUCCEEDED -> successfull++;
                case FAILED -> failed++;
                case ERRORED -> errored++;

                // Display error about the internal error
                case INTERNAL_ERROR -> {
                    encounteredInternalError = true;
                    testDisplay.showPanel(TestDisplay.DisplayType.MESSAGE);
                    testDisplay.getMessageBox().setMessage(
                            "An internal error occurred",
                            result.getMessage());
                }
            }
        }

        if (!encounteredInternalError) {
            testDisplay.getSummaryPanel().setLabels(successfull, failed, errored);
            testDisplay.showPanel(TestDisplay.DisplayType.RESULT);
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