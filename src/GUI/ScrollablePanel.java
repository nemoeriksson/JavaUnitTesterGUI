package GUI;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public void createNewLabel(String content) {
        Matcher regexer = regexPattern.matcher(content);

        if (regexer.find()) {
            String part1 = regexer.group(1);
            String part2 = regexer.group(2);
            String part3 = content.substring(part1.length()+part2.length());

            addTextToStyleDocument(part1, defaultText);
            addTextToStyleDocument(part2, part2.equals("PASSED") ? greenText : redText);
            addTextToStyleDocument(String.format("%s\n\n", part3), redText);
        }
        // No match found - use default for all
        else {
            addTextToStyleDocument(content, defaultText);
        }

        revalidate();
        repaint();
    }

    public void reset() {
        contentPanel.setText("");
    }

    // Private methods

    private void addTextToStyleDocument(String content, Style style) {
        try {
            styleDocument.insertString(styleDocument.getLength(), content, style);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    private Style generateStyle(Color color) {
        Style style = styleDocument.addStyle("FailedStyle", null);
        StyleConstants.setForeground(style, color);
        StyleConstants.setBold(style, true);
        return style;
    }
}