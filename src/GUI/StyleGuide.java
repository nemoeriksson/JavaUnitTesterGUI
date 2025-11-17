package GUI;

import java.awt.*;

/**
 * A class containing information about
 * different colors. Can be used as a
 * color scheme.
 *
 * @author c24nen
 * @version 25.11.17
 */
public class StyleGuide {
    private Color mainColor = Color.magenta;
    private Color white = Color.white;
    private Color lightGray = Color.lightGray;
    private Color borderColor = Color.lightGray;

    /*
     Getters and setters for the different aspects of the wanted style.
     */

    public void setMainColor(Color c) { mainColor = c; }
    public Color getMainColor() { return mainColor; }

    public void setWhite(Color c) { white = c; }
    public Color getWhite() { return white; }

    public void setLightGray(Color c) { lightGray = c; }
    public Color getLightGray() { return lightGray; }

    public void setBorderColor(Color c) { borderColor = c; }
    public Color getBorderColor() { return borderColor; }
}
