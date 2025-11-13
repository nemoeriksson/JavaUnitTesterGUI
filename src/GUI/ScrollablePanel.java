package GUI;

import javax.swing.*;
import java.awt.*;

public class ScrollablePanel extends JScrollPane {
    private JPanel contentPanel;

    public ScrollablePanel() {
        super();

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setViewportView(contentPanel);
    }

    public void createNewLabel(String content) {
        JLabel label = new JLabel(content);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(label);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void reset() {
        //contentPanel.removeAll();
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}