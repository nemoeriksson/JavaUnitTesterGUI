package GUI;

import javax.swing.*;
import java.awt.*;

public class Header {
    private final JPanel mainPanel;
    private final FlowLayout flowLayout;
    private final JComboBox<String> searchBar;
    private final JButton runButton;

    public Header(Dimension size) {
        flowLayout = new FlowLayout(FlowLayout.CENTER);

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
                BorderFactory.createLineBorder(Color.lightGray, 2)
        ));
        button.setBackground(new Color(255,255,255));

        return button;
    }

    private JComboBox<String> generateSearchBar() {
        JComboBox<String> searchBar = new JComboBox<>();
        searchBar.setEditable(true);
        searchBar.setPreferredSize(new Dimension(200, 24));
        searchBar.setBackground(new Color(255,255,255));
        searchBar.setSelectedItem("");
        searchBar.addItem("Testing");
        searchBar.addItem("Another item");
        ((JTextField)searchBar.getEditor().getEditorComponent()).setBorder(BorderFactory.createEmptyBorder(0,4,0,0));

        return searchBar;
    }

    private JPanel generateMainPanel(Dimension size) {
        JPanel mainPanel = new JPanel();
        mainPanel.setSize(size);
        mainPanel.setBackground(new Color(0,122,122));
        mainPanel.setLayout(flowLayout);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(6,0,0,0));

        return mainPanel;
    }
}
