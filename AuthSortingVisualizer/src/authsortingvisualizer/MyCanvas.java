package authsortingvisualizer;

import java.awt.Canvas;
import java.awt.Graphics;

public class MyCanvas extends Canvas {
    public static final long serialVersionUID = 2L;

    private VisualizerProvider listener;

    // Constructor
    public MyCanvas(VisualizerProvider listener) {
        super();
        this.listener = listener;
    }

    // Override paint method to draw on the canvas
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        clear(g);

        // Notify the listener to draw the array
        listener.onDrawArray();
    }

    // Clear the canvas by filling it with the background color
    public void clear(Graphics g) {
        g.setColor(ColorManager.CANVAS_BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    // Interface for providing a visualizer to draw on the canvas
    public interface VisualizerProvider {
        void onDrawArray();
    }
}
