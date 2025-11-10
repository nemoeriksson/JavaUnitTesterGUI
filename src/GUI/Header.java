package GUI;

import javax.swing.*;
import java.awt.*;

public class Header {
    private final JPanel mainPanel;
    private final JComboBox<String> searchBar;
    private final JButton runButton;
    private final FlowLayout layout;
    private final StyleGuide style;

    public Header(Dimension size, StyleGuide style) {
        this.style = style;

        layout = new FlowLayout(FlowLayout.CENTER);
        runButton = generateRunButton();
        searchBar = generateSearchBar();

        mainPanel = generateMainPanel(size);

        mainPanel.add(searchBar);
        mainPanel.add(Box.createHorizontalStrut(120));
        mainPanel.add(runButton);
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    private JButton generateRunButton() {
        JButton button = new JButton("Run");
        button.setPreferredSize(new Dimension(64, 24));

        // TODO: Change to use style guide class
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0,0,0,0),
                BorderFactory.createLineBorder(style.getBorderColor(), 2)
        ));
        button.setBackground(style.getWhite());

        return button;
    }

    private JComboBox<String> generateSearchBar() {
        JComboBox<String> searchBar = new JComboBox<>();
        searchBar.setEditable(true);
        searchBar.setPreferredSize(new Dimension(200, 24));
        searchBar.setBackground(style.getWhite());
        searchBar.setSelectedItem("");
        searchBar.addItem("Testing");
        searchBar.addItem("Another item");
        ((JTextField)searchBar.getEditor().getEditorComponent()).setBorder(BorderFactory.createEmptyBorder(0,4,0,0));

        return searchBar;
    }

    private JPanel generateMainPanel(Dimension size) {
        JPanel mainPanel = new JPanel();
        mainPanel.setSize(size);
        mainPanel.setBackground(style.getMainColor());
        mainPanel.setLayout(this.layout);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(6,0,0,0));

        return mainPanel;
    }
}
