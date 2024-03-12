package authsortingvisualizer;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.awt.Font;

public class Bar {
    private final int MARGIN = 1;
    private int x, y, width, value;
    private Color color;

    // Constructor
    // y: the bottom left corner
    public Bar(int x, int y, int width, int value, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.value = value;
        this.color = color;
    }

    // Draw the bar on the graphics context
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x + MARGIN, y - value, width - MARGIN * 2, value);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 10));
        
        // Adjusted x-coordinate to center
        int valueX = x + (width - g.getFontMetrics().stringWidth(Integer.toString(value))) / 2;

        g.drawString(Integer.toString(value), valueX, y - value - 5);
    }

    // Clear the space occupied by the bar on the graphics context
    public void clear(Graphics g) {
        // Clear the space
        g.setColor(ColorManager.CANVAS_BACKGROUND);
        g.fillRect(x + MARGIN, y - value, width - MARGIN * 2, value);
        
        // Clear the space occupied by the text
        g.setColor(ColorManager.CANVAS_BACKGROUND);
        int textHeight = 15; // Assuming a fixed height for the text area
        g.fillRect(x, y - value - textHeight, width, textHeight);
    }

    // Setter for bar value
    public void setValue(int value) {
        this.value = value;
    }

    // Getter for bar value
    public int getValue() {
        return value;
    }

    // Setter for bar color
    public void setColor(Color color) {
        this.color = color;
    }

    // Getter for bar color
    public Color getColor() {
        return color;
    }
}
