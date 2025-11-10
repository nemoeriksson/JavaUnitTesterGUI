package GUI;

import java.awt.*;

public class StyleGuide {
    private Color mainColor = Color.magenta;
    private Color white = Color.white;
    private Color borderColor = Color.lightGray;

    public void setMainColor(Color c) { mainColor = c; }
    public Color getMainColor() { return mainColor; }
    
    public void setWhite(Color c) { white = c; }
    public Color getWhite() { return white; }

    public void setBorderColor(Color c) { borderColor = c; }
    public Color getBorderColor() { return borderColor; }
}
