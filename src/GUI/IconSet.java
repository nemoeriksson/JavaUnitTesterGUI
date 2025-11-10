package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class IconSet<T>{
    private Map<T, ImageIcon> icons = new HashMap();

    public void addIcon(T key, String resourcePath) {
        icons.put(key, getImageIcon(resourcePath));
    }

    public ImageIcon getIcon(T key) { return icons.get(key); }

    private ImageIcon getImageIcon(String resourcePath) {
        URL url = getClass().getResource(resourcePath);
        return new ImageIcon(Toolkit.getDefaultToolkit().getImage(url));
    }
}
