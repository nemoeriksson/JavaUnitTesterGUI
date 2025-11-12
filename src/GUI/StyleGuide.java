package GUI;

import javax.swing.*;
import java.awt.*;

public class StyleGuide {
    private final IconSet<IconType> icons = new IconSet<>();
    private Color mainColor = Color.magenta;
    private Color white = Color.white;
    private Color lightGray = Color.lightGray;
    private Color borderColor = Color.lightGray;

    public enum IconType {
        INFO, SUCCESS, FAILURE, ERROR
    }

    public void setIcon(IconType type, String resourcePath) {
        icons.addIcon(type, resourcePath);
    }

    public ImageIcon getIcon(IconType type) {
        return icons.getIcon(type);
    }

    public void setMainColor(Color c) { mainColor = c; }
    public Color getMainColor() { return mainColor; }

    public void setWhite(Color c) { white = c; }
    public Color getWhite() { return white; }

    public void setLightGray(Color c) { lightGray = c; }
    public Color getLightGray() { return lightGray; }

    public void setBorderColor(Color c) { borderColor = c; }
    public Color getBorderColor() { return borderColor; }
}
