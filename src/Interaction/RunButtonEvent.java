package Interaction;

import Core.TestExecutor;
import Core.TestResult;
import GUI.Header;
import GUI.ContentDisplay;
import Core.TestInfoCollection;
import GUI.ScrollablePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RunButtonEvent implements ActionListener {
    private JComboBox<String> searchBar;
    private ContentDisplay contentDisplay;
    private TestInfoCollection tester;

    public RunButtonEvent(TestInfoCollection tester, Header header, ContentDisplay contentDisplay) {
        searchBar = header.getSearchBar();
        this.contentDisplay = contentDisplay;
        this.tester = tester;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedItem = (String) searchBar.getSelectedItem();

        if (!validateAlternative(selectedItem)) {
            // Open message box and display error message
            contentDisplay.showPanel(ContentDisplay.DisplayType.MESSAGE);
            displayError(selectedItem);
            return;
        }

        // Run all tests and get results
        TestExecutor executor = new TestExecutor(tester.getTestInfo(selectedItem));

        contentDisplay.reset();

        List<TestResult> testResults = executor.doInBackground();

        int successfull = 0;
        int failed = 0;
        int errored = 0;

        boolean encounteredInternalError = false;
        ScrollablePanel scrollablePanel = contentDisplay.getScrollablePanel();

        for (TestResult result : testResults) {
            switch (result.getStatus()) {
                case SUCCEEDED -> successfull++;
                case FAILED -> failed++;
                case ERRORED -> errored++;

                // Display error about the internal error
                case INTERNAL_ERROR -> {
                    encounteredInternalError = true;
                    contentDisplay.showPanel(ContentDisplay.DisplayType.MESSAGE);
                    contentDisplay.getMessageBox().setMessage(
                            "An internal error occurred",
                            result.getMessage());
                }
            }

            scrollablePanel.createNewLabel(result.getMessage());
        }

        if (!encounteredInternalError) {
            contentDisplay.getSummaryPanel().setLabels(successfull, failed, errored);
            contentDisplay.showPanel(ContentDisplay.DisplayType.RESULT);
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