package x.mvmn.games.tetris.oopexamples.графіка.допоміжне;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;

public class ColorPreview extends JComponent {

    private volatile Color color = Color.WHITE;

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.setColor(color);
        Dimension size = this.getSize();
        Rectangle rect = new Rectangle(0, 0, size.width, size.height);
        if (this.getBorder() != null) {
            Insets insets = this.getBorder().getBorderInsets(this);
            rect.x += insets.left;
            rect.y += insets.top;
            rect.width -= rect.x + insets.right;
            rect.height -= rect.y + insets.bottom;
        }
        graphics.fillRect(rect.x, rect.y, rect.width, rect.height);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}