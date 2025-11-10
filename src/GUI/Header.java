package GUI;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {
    private final JComboBox<String> searchBar;
    private final JButton runButton;
    private final FlowLayout layout;

    public Header(Dimension size, StyleGuide style) {
        layout = new FlowLayout(FlowLayout.CENTER);
        runButton = generateRunButton(style);
        searchBar = generateSearchBar(style);

        setPreferredSize(size);
        setBackground(style.getMainColor());
        setLayout(this.layout);
        setBorder(BorderFactory.createEmptyBorder(6,0,0,0));

        add(searchBar);
        add(Box.createHorizontalStrut(120));
        add(runButton);
    }

    private JButton generateRunButton(StyleGuide style) {
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

    private JComboBox<String> generateSearchBar(StyleGuide style) {
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
}
