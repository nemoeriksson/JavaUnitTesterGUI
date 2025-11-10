package GUI;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private final BorderLayout layout = new BorderLayout();

    private final Header header;
    private final TestDisplay mainSection;

    public Window(String title, Dimension size, StyleGuide style) {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(size);
        setLocation(200, 200);
        setResizable(false);

        header = new Header(new Dimension(size.width, 48), style);
        mainSection = new TestDisplay(style);

        setLayout(layout);
        add(header, BorderLayout.NORTH);
        add(mainSection, BorderLayout.CENTER);
        pack();
    }
}
