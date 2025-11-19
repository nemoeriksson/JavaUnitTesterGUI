package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Main class for the unit tester GUI,
 * contains a header bar and a content
 * display for result details and
 * eventual error messages.
 *
 * @author c24nen
 * @version 25.11.17
 */
public class Window extends JFrame {
    private final BorderLayout layout = new BorderLayout();

    private final Header header;
    private final ContentDisplay mainSection;

    public Window(String title, Dimension size, StyleGuide style) {
        super(title);

        // Setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(200, 200);
        setPreferredSize(size);
        setMinimumSize(size);

        // Create sub-panels
        header = new Header(new Dimension(size.width, 48), style);
        mainSection = new ContentDisplay(style);

        setLayout(layout);
        add(header, BorderLayout.NORTH);
        add(mainSection, BorderLayout.CENTER);
        pack();
    }

    /**
     * Gets the UI's header panel.
     *
     * @return The UI Header panel
     */
    public Header getHeader() { return header; }

    /**
     * Gets the UI's content display.
     *
     * @return The UI content display panel
     */
    public ContentDisplay getContentDisplay() { return mainSection; }
}
