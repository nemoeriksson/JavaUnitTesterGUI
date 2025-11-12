package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class IconSet<T>{
    private Map<T, ImageIcon> icons = new HashMap();

    // Public methods

    public void addIcon(T key, String resourcePath) {
        icons.put(key, getImageIcon(resourcePath, 20));
    }

    public ImageIcon getIcon(T key) { return icons.get(key); }

    // Private methods

    private ImageIcon getImageIcon(String resourcePath, int size) {
        URL url = getClass().getResource(resourcePath);
        BufferedImage icon;

        try {
            icon = ImageIO.read(url);
        }
        catch (IOException e) {
            System.out.println("Unable to load image from " + url);
            throw new RuntimeException(e);
        }

        return new ImageIcon(resizeImage(icon, size));
    }

    private BufferedImage resizeImage(BufferedImage original, int size) {
        BufferedImage resizedImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();

        // Enable high-quality rendering
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(original, 0, 0, size, size, null);
        g2d.dispose();

        return resizedImage;
    }
}
