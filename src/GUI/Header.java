package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * A class for the UI's header panel.
 *
 * @author c24nen
 * @version 25.11.17
 */
public class Header extends JPanel {
    private final JComboBox<String> searchBar;
    private final JButton runButton;
    private final FlowLayout layout;

    public Header(Dimension size, StyleGuide style) {
        // Setup
        setPreferredSize(size);
        setBackground(style.getMainColor());
        setBorder(BorderFactory.createEmptyBorder(6,0,0,0));
        layout = new FlowLayout(FlowLayout.CENTER);
        setLayout(this.layout);

        // Generate sub-panels
        runButton = generateRunButton(style);
        searchBar = generateSearchBar(style);

        add(searchBar);
        add(Box.createHorizontalStrut(120));
        add(runButton);
    }

    // Public methods

    /**
     * Adds a list of valid options for the search
     * bar drop down.
     *
     * @param options A list of selectable options
     */
    public void setSearchBarAlternatives(List<String> options) {
        for (String option : options) {
            searchBar.addItem(option);
        }
    }

    /**
     * Sets the action listener for the Run button. Removes
     * any previous.
     *
     * @param actionListener
     */
    public void setRunButtonActionListener(ActionListener actionListener) {
        // Remove any current
        for (ActionListener al : runButton.getActionListeners()) {
            runButton.removeActionListener(al);
        }

        // Add specified
        runButton.addActionListener(actionListener);
    }

    /**
     * Gets the search bar.
     *
     * @return The search bar element
     */
    public JComboBox<String> getSearchBar() { return searchBar; }

    // Private methods

    /**
     * Generates the run button according to style
     * guide.
     *
     * @param style Style guide for the UI
     * @return A new "Run" button
     */
    private JButton generateRunButton(StyleGuide style) {
        JButton button = new JButton("Run");
        button.setPreferredSize(new Dimension(64, 24));

        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0,0,0,0),
                BorderFactory.createLineBorder(style.getBorderColor(), 2)
        ));
        button.setBackground(style.getWhite());

        return button;
    }

    /**
     * Generates the search bar element according
     * to the style guide.
     *
     * @param style The UI's style guide
     * @return A new search bar element
     */
    private JComboBox<String> generateSearchBar(StyleGuide style) {
        JComboBox<String> searchBar = new JComboBox<>();
        searchBar.setEditable(true);
        searchBar.setPreferredSize(new Dimension(200, 24));
        searchBar.setBackground(style.getWhite());
        searchBar.setSelectedItem("");
        ((JTextField)searchBar.getEditor().getEditorComponent()).setBorder(BorderFactory.createEmptyBorder(0,4,0,0));

        return searchBar;
    }
}
