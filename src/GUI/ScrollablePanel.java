package GUI;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class for the scrollable panel
 * containing styled tests about the
 * results of tests.
 *
 * @author c24nen
 * @version 25.11.17
 */
public class ScrollablePanel extends JScrollPane {
    private final JTextPane contentPanel;
    private final StyledDocument styleDocument;

    private final Style redText;
    private final Style greenText;
    private final Style defaultText;

    private final Pattern regexPattern;

    public ScrollablePanel() {
        super();
        regexPattern = Pattern.compile("(^Test \\d*: .* - )(PASSED|FAILED|ERROR)(.*)");

        // Create content panel
        contentPanel = new JTextPane();
        contentPanel.setBackground(getBackground());
        contentPanel.setEditable(false);

        styleDocument = contentPanel.getStyledDocument();

        // Create styles for better looking text
        redText = generateStyle(new Color(183, 65, 14));
        greenText = generateStyle(new Color(34, 139, 34));
        defaultText = generateStyle(new Color(0,0,0));

        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setViewportView(contentPanel);
    }

    // Public methods

    /**
     * Appends a new label with the specified content
     * styled to better visualize test results.
     *
     * @param content New content to append
     */
    public void createNewLabel(String content) {
        Matcher regexer = regexPattern.matcher(content);

        // Style content if it matches the test result format
        if (regexer.find()) {
            String part1 = regexer.group(1);
            String part2 = regexer.group(2);
            String part3 = content.substring(part1.length()+part2.length());

            addTextToStyleDocument(part1, defaultText);
            addTextToStyleDocument(part2, part2.equals("PASSED") ? greenText : redText);
            addTextToStyleDocument(String.format("%s\n\n", part3), redText);
        }
        // No match found - use default style for all
        else {
            addTextToStyleDocument(content, defaultText);
        }

        revalidate();
        repaint();
    }

    /**
     * Deletes all text.
     */
    public void reset() {
        contentPanel.setText("");
    }

    // Private methods

    /**
     * Adds text with specified style to the
     * content panel.
     *
     * @param content Content to append
     * @param style Style to use
     */
    private void addTextToStyleDocument(String content, Style style) {
        try {
            styleDocument.insertString(styleDocument.getLength(), content, style);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates a new style with a specified color.
     *
     * @param color Color to use for style
     * @return New style
     */
    private Style generateStyle(Color color) {
        Style style = styleDocument.addStyle("FailedStyle", null);
        StyleConstants.setForeground(style, color);
        StyleConstants.setBold(style, true);
        return style;
    }
}