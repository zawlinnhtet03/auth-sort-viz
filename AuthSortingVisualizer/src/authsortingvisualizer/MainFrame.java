package authsortingvisualizer;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferStrategy;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.text.NumberFormat;

public class MainFrame extends JFrame implements PropertyChangeListener,
        ChangeListener, Visualizer.SortedListener,
        ButtonPanel.SortButtonListener, MyCanvas.VisualizerProvider {
    public static final long serialVersionUID = 10L;

    // Constants for window dimensions, array capacity, and frames per second
    private static final int WIDTH = 1500, HEIGHT = 800;
    private static final int CAPACITY = 30, FPS = 100;

    // UI components
    private JPanel mainPanel, inputPanel, sliderPanel, inforPanel;
    private ButtonPanel buttonPanel;
    private JLabel capacityLabel, fpsLabel, timeLabel, compLabel, swapLabel;
    private JFormattedTextField capacityField;
    private JSlider fpsSlider;
    private MyCanvas canvas;
    private Visualizer visualizer;

    // Constructor
    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMaximumSize(new Dimension(WIDTH, HEIGHT + 200));
        setMinimumSize(new Dimension(WIDTH, HEIGHT + 20));
        setPreferredSize(new Dimension(WIDTH, HEIGHT + 20));
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(ColorManager.BACKGROUND);
        
        setIcon();
        
        // Initialize UI components
        initialize();
    }

    // Initialize UI components and layout
    private void initialize() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(ColorManager.BACKGROUND);
        add(mainPanel);

        // Buttons panel
        buttonPanel = new ButtonPanel(this);
        buttonPanel.setBounds(0, 150, 250, HEIGHT);
        buttonPanel.setBackground(ColorManager.BACKGROUND);
        mainPanel.add(buttonPanel);

        // Canvas
        canvas = new MyCanvas(this);
        int cWidth = WIDTH - 260;
        int cHeight = HEIGHT - 80;
        canvas.setFocusable(false);
        canvas.setMaximumSize(new Dimension(cWidth, cHeight));
        canvas.setMinimumSize(new Dimension(cWidth, cHeight));
        canvas.setPreferredSize(new Dimension(cWidth, cHeight));
        canvas.setBounds(250, 60, cWidth, cHeight);
        mainPanel.add(canvas);
        pack();

        // Sorting visualizer
        visualizer = new Visualizer(CAPACITY, FPS, this);
        visualizer.createRandomArray(canvas.getWidth(), canvas.getHeight());

        // Input field for capacity
        // labels
        capacityLabel = new JLabel("Capacity");
        capacityLabel.setForeground(ColorManager.TEXT);
        capacityLabel.setFont(new Font(null, Font.BOLD, 15));

        // capacity input fields
        NumberFormat format = NumberFormat.getNumberInstance();
        MyFormatter formatter = new MyFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(65);
        formatter.setAllowsInvalid(false);
        /* importan -> onChange */
        formatter.setCommitsOnValidEdit(true);

        // PropertyChangeListener for capacity changes
        capacityField = new JFormattedTextField(formatter);
        capacityField.setValue(CAPACITY);
        capacityField.setColumns(3);
        capacityField.setFont(new Font(null, Font.PLAIN, 15));
        capacityField.setForeground(ColorManager.TEXT);
        capacityField.setBackground(ColorManager.CANVAS_BACKGROUND);
        capacityField.setBorder(BorderFactory.createLineBorder(ColorManager.FIELD_BORDER, 1));
        capacityField.addPropertyChangeListener("value", this);
 
        capacityLabel.setLabelFor(capacityField);

        // Input panel layout
        inputPanel = new JPanel(new GridLayout(1, 0));
        inputPanel.add(capacityLabel);
        inputPanel.add(capacityField);
        inputPanel.setBackground(ColorManager.BACKGROUND);
        inputPanel.setBounds(25, 20, 150, 30);
        mainPanel.add(inputPanel);

        // Slider for FPS
        fpsLabel = new JLabel("Frames Per Second");
        fpsLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        fpsLabel.setFont(new Font(null, Font.BOLD, 15));
        fpsLabel.setForeground(ColorManager.TEXT);
        fpsLabel.setForeground(Color.BLACK);

        // slider
        fpsSlider = new JSlider(JSlider.HORIZONTAL, 50, 350, FPS);
        fpsSlider.setMajorTickSpacing(100);
        fpsSlider.setMinorTickSpacing(20);
        fpsSlider.setPaintTicks(true);
        fpsSlider.setPaintLabels(true);
        fpsSlider.setPaintTrack(true);
        fpsSlider.setForeground(ColorManager.TEXT);
        fpsSlider.setForeground(Color.BLACK);
        fpsSlider.addChangeListener(this);     

        // Slider settings and layout
        sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
        sliderPanel.setBackground(ColorManager.BACKGROUND);
        sliderPanel.setBackground(Color.WHITE);
        sliderPanel.add(fpsLabel);
        sliderPanel.add(fpsSlider);
        sliderPanel.setBounds(10, 80, 220, 100);
        mainPanel.add(sliderPanel);  
        
        // Statistics panel
        // elapsed time
        timeLabel = new JLabel("Elapsed time: 0");
        timeLabel.setFont(new Font(null, Font.PLAIN, 15));
        timeLabel.setForeground(ColorManager.TEXT_RED);

        // comparisons
        compLabel = new JLabel("Comparisons: 0");
        compLabel.setFont(new Font(null, Font.PLAIN, 15));
        compLabel.setForeground(ColorManager.TEXT_CYAN);

        // swapping
        swapLabel = new JLabel("Swaps: 0");
        swapLabel.setFont(new Font(null, Font.PLAIN, 15));
        swapLabel.setForeground(ColorManager.TEXT_GREEN);
        
        // Terminate Program button
        JButton terminateButton = new JButton("Interrupt Process");
        terminateButton.setFont(new Font("Arial", Font.BOLD, 14));
        terminateButton.setForeground(Color.WHITE);
        terminateButton.setBackground(Color.RED);
        terminateButton.setFocusPainted(false); // Remove focus border
        terminateButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25)); // Add padding
        // Add a shadow effect
        terminateButton.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.BLACK), // Outer border
        BorderFactory.createEmptyBorder(5, 15, 5, 15) // Inner padding
));

        // Set the position and size of the button
        terminateButton.setBounds(1190, 20, 200, 30);
        mainPanel.add(terminateButton);
        
         // Add hover effect
        terminateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                terminateButton.setBorder(BorderFactory.createLineBorder(Color.WHITE)); // Change border color on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                terminateButton.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Change border color on hover
            }

        });

        // Add click effect
        terminateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                terminateButton.setBorder(BorderFactory.createLineBorder(Color.WHITE)); // Slightly darker blue on click
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                terminateButton.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Restore hover color on release
            }
        });
        
         // Add ActionListener
        terminateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Terminate the program
                if (visualizer != null) {
                    visualizer.stopSorting();
                }
            }
        });

        // Statistics panel layout
        inforPanel = new JPanel(new GridLayout(1, 0));
        inforPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        inforPanel.add(timeLabel);
        inforPanel.add(compLabel);
        inforPanel.add(swapLabel);
        inforPanel.setBackground(ColorManager.BACKGROUND);
        inforPanel.setBounds(410, 20, 800, 30);
        mainPanel.add(inforPanel);
    }

    /* IMPLEMENTED METHODS */

    // Capacity change listener
    public void propertyChange(PropertyChangeEvent e) {
        // update capacity
        int value = ((Number) capacityField.getValue()).intValue();
        visualizer.setCapacity(value);
    }

    // FPS change listener
    public void stateChanged(ChangeEvent e) {
        if (!fpsSlider.getValueIsAdjusting()) {
            int value = (int) fpsSlider.getValue();
            visualizer.setFPS(value);
        }
    }

    // Button click listener
    public void sortButtonClicked(int id) {
        switch (id) {
            case 0:  // create button
                visualizer.createRandomArray(canvas.getWidth(), canvas.getHeight());
                break;
            case 1:  // bubble button
                visualizer.bubbleSort();
                break;
            case 2:  // selection button
                visualizer.selectionSort();
                break;
            case 3:  // insertion button
                visualizer.insertionSort();
                break;
            case 4:  // quick button
                visualizer.quickSort();
                break;
            case 5:  // merge button
                visualizer.mergeSort();
                break;
            case 6:  // exit button
                System.exit(0);
                break;    
        }
    }

    // Draw the array
    public void onDrawArray() {
        if (visualizer != null)
            visualizer.drawArray();
    }

    // Show statistics when sorting is completed
    public void onArraySorted(long elapsedTime, int comp, int swapping) {
        timeLabel.setText("Elapsed Time: " + (int) (elapsedTime) + "  ms");
        compLabel.setText("Comparisons: " + comp);
        swapLabel.setText("Swaps: " + swapping);
    }

    // Get the graphics for drawing
    public BufferStrategy getBufferStrategy() {
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(2);
            bs = canvas.getBufferStrategy();
        }

        return bs;
    }
    
    private void setIcon() {
        ImageIcon icon = new ImageIcon(getClass().getResource("bar.png"));
        setIconImage(icon.getImage());
    }
    
    public void setUser(String userName) {
        setTitle("Sorting Visualizer [" + userName + "]");
    }
}


