package Intermediate;

import GUI.Header;
import GUI.TestDisplay;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunButtonEvent implements ActionListener {
    private JComboBox<String> searchBar;
    private TestDisplay testDisplay;

    public RunButtonEvent(Header header, TestDisplay testDisplay) {
        searchBar = header.getSearchBar();
        this.testDisplay = testDisplay;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedItem = (String)searchBar.getSelectedItem();

        if (validateAlternative(selectedItem)) {
            // Open results panel and run setup, tests, and teardown.
            System.out.println("Valid test class");
            testDisplay.showPanel(TestDisplay.DisplayType.RESULT);
        }
        else { // Invalid alternative
            // Open message box and display error message
            testDisplay.showPanel(TestDisplay.DisplayType.MESSAGE);

            if (selectedItem.equals("")) {
                testDisplay.getMessageBox().setMessage(
                        "Empty test class name",
                        "Name of test class can not be empty. Please enter a valid test class or select one from the dropdown."
                );
            } else { // Default error message
                testDisplay.getMessageBox().setMessage(
                        "Invalid test class",
                        String.format("No test class named '%s' found. Please enter a valid test class or select one from the dropdown.", selectedItem)
                );
            }
        }
    }

    private boolean validateAlternative(String selectedItem) {
        for (int i = 0; i < searchBar.getItemCount(); i++) {
            if (searchBar.getItemAt(i).equals(selectedItem))
                return true;
        }

        return false;
    }
}