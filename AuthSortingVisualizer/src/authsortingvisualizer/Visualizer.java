package authsortingvisualizer;

import javax.swing.JOptionPane;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.Graphics;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Visualizer {
    // Constants for visualizing bars
    private static final int PADDING = 20;
    private static final int MAX_BAR_HEIGHT = 370, MIN_BAR_HEIGHT = 70;

    // Array and sorting-related variables
    private Integer[] array;
    private int capacity, speed;
    private Bar[] bars;
    private boolean hasArray;

    // Statistics variables
    private long startTime, time;
    private int comp, swapping;

    // Colors for visualization
    private Color originalColor, swappingColor, comparingColor;

    // Graphics objects
    private BufferStrategy bs;
    private Graphics g;

    // Listener for sorting events
    private SortedListener listener;
    
     // Terminating Code
    private volatile boolean sortingInProgress = false;
    private volatile boolean stopRequested = false;
    private Thread sortingThread;

    // Constructor
    public Visualizer(int capacity, int fps, SortedListener listener) {
        // Initialization of variables
        this.capacity = capacity;
        this.speed = (int) (50000.0 / fps);
        this.listener = listener;
        startTime = time = comp = swapping = 0;

        originalColor = ColorManager.BAR_WHITE;
        comparingColor = Color.CYAN;
        swappingColor = ColorManager.BAR_GREEN;

        bs = listener.getBufferStrategy();

        hasArray = false;
        
        // terminating code
        sortingInProgress = false;
        stopRequested = false;
        sortingThread = null;
    }

    // Method to create a random array for visualization
    public void createRandomArray(int canvasWidth, int canvasHeight) {
        // Initialization of array and bars
        array = new Integer[capacity];
        bars = new Bar[capacity];
        hasArray = true;

        // Initial position for drawing bars
        double x = PADDING;
        int y = canvasHeight - PADDING;

        // Width of all bars
        double width = (double) (canvasWidth - PADDING * 2) / capacity;

        // Get graphics object
        g = bs.getDrawGraphics();
        g.setColor(ColorManager.CANVAS_BACKGROUND);
        g.fillRect(0, 0, canvasWidth, canvasHeight);

        Random rand = new Random();
        int value;
        Bar bar;
        for (int i = 0; i < array.length; i++) {
            // Generate random bar height
            value = rand.nextInt(MAX_BAR_HEIGHT) + MIN_BAR_HEIGHT;
            array[i] = value;

            // Create and draw bar
            bar = new Bar((int) x, y, (int) width, value, originalColor);
            bar.draw(g);
            bars[i] = bar;

            // Move to the next bar
            x += width;
        }

        bs.show();
        g.dispose();
    }

    // Method to return a color for a bar based on its value
    private Color getBarColor(int value) {
        int interval = (int) (array.length / 5.0);
        return ColorManager.BAR_PURE;
    }
    
    public void startSorting() {

        // stopSorting();
        if (sortingThread != null && !sortingThread.isAlive()) {
            stopRequested = false; // Reset stopRequested flag
            sortingInProgress = true;
            sortingThread.start(); // Start the sorting thread

        }
    }

    public void stopSorting() {
        stopRequested = true;
        if (sortingThread != null && sortingThread.isAlive()) {
            try {
                sortingThread.join(); // Wait for the thread to terminate
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

     // BUBBLE SORT
    public void bubbleSort() {
        sortingInProgress = true;
        sortingThread = new Thread(() -> {
            if (!isCreated())
                return;
            
            // Warm-up phase
            Sort.bubbleSort(array.clone());

            // Get graphics object
            g = bs.getDrawGraphics();
            
            // Calculate elapsed time
            startTime = System.nanoTime();
            Sort.bubbleSort(array.clone());
            time = System.nanoTime() - startTime;

            comp = swapping = 0;
            int count = 0;
            for (int i = array.length - 1; i >= 0; i--) {
                count = 0;
                for (int j = 0; j < i; j++) {
                    colorPair(j, j + 1, comparingColor);

                    if (array[j] > array[j + 1]) {
                        swap(j, j + 1);
                        count++;
                        swapping++;
                    }

                    comp++;

                    // check if stop is requested
                    if (stopRequested) {
                        sortingInProgress = false; // Exit the sorting process
                        g.dispose();
                        return;
                    }
                }

                bars[i].setColor(getBarColor(i));
                bars[i].draw(g);
                bs.show();

                if (count == 0)  // The array is sorted
                    break;
            }

            finishAnimation();

            g.dispose();
        });
        startSorting();// Start the thread
    }

    /* SELECTION SORT */
    public void selectionSort() {
        sortingInProgress = true;
        sortingThread = new Thread(() -> {
            if (!isCreated())
                return;

            // Warm-up phase
            Sort.selectionSort(array.clone());    
            
            // Get graphics object
            g = bs.getDrawGraphics();

            // Calculate elapsed time
            startTime = System.nanoTime();
            Sort.selectionSort(array.clone());
            time = System.nanoTime() - startTime;

            comp = swapping = 0;
            for (int i = 0; i < array.length; i++) {
                // Find the min
                int min = array[i], index = i;
                for (int j = i; j < array.length; j++) {
                    if (min > array[j]) {
                        min = array[j];
                        index = j;
                    }

                    colorPair(index, j, comparingColor);
                    comp++;
                    
                    if(stopRequested){
                        sortingInProgress = false;
                        g.dispose();
                        return;
                    }
                }

                swap(i, index);
                swapping++;

                bars[i].setColor(getBarColor(i));
                bars[i].draw(g);
                bs.show();
            }

            finishAnimation();

            g.dispose();
        });
        startSorting();// Start the thread
    }

    /* INSERTION SORT */
    public void insertionSort() {
        sortingInProgress = true;
        sortingThread = new Thread(() -> {
            if (!isCreated())
                return;

            // Warm-up phase
            Sort.insertionSort(array.clone());
            
            // Get graphics object
            g = bs.getDrawGraphics();

            // Calculate elapsed time
            startTime = System.nanoTime();
            Sort.insertionSort(array.clone());
            time = System.nanoTime() - startTime;

            comp = swapping = 0;

            Bar bar;
            for (int i = 1; i < array.length; i++) {
                bars[i].setColor(getBarColor(i));

                // Find the insertion location by comparing to its predecessor
                int index = i - 1, element = array[i];
                while (index >= 0 && element < array[index]) {
                    array[index + 1] = array[index];

                    bar = bars[index + 1];
                    bar.clear(g);
                    bar.setValue(bars[index].getValue());
                    colorBar(index + 1, swappingColor);

                    index--;
                    comp++;
                    swapping++;

                    if(stopRequested){
                        sortingInProgress = false;
                        g.dispose();
                        return;
                    }
                }
                comp++;

                index++;

                // Insert the element
                array[index] = element;

                bar = bars[index];
                bar.clear(g);
                bar.setValue(element);
                bar.setColor(getBarColor(index));
                bar.draw(g);

                bs.show();
            }

            finishAnimation();

            g.dispose();
        });
        startSorting();// Start the thread
    }

    /* QUICK SORT */
    public void quickSort() { 
        sortingInProgress = true;
        sortingThread = new Thread(() -> {
            if (!isCreated())
                return;

            // Warm-up phase
            Sort.quickSort(array.clone());
            
            g = bs.getDrawGraphics();

            // Calculate elapsed time
            startTime = System.nanoTime();
            
            Sort.quickSort(array.clone());
            time = System.nanoTime() - startTime;

            comp = swapping = 0;

            quickSort(0, array.length - 1);
            
            // Update the sortingInProgress flag accordingly
            sortingInProgress = false;

            if (stopRequested) {
                sortingInProgress = false;
                g.dispose();
                return;
            } else {
                finishAnimation();
            }
            
            g.dispose();
        });
        startSorting();// Start the thread
    }

    // Recursive quicksort
    private void quickSort(int start, int end) {
        if (start < end && sortingInProgress) {
            
            if (stopRequested) {
                sortingInProgress = false;
                g.dispose();
                return;
            }
            // Place pivot in correct spot
            int pivot = partition(start, end);

            // Coloring
            bars[pivot].setColor(getBarColor(pivot));
            bars[pivot].draw(g);
            bs.show();

            // Sort the left half
            quickSort(start, pivot - 1);

            // Sort the right half
            quickSort(pivot + 1, end);    
        }
    }

    // Quick sort partition
    private int partition(int start, int end) {
        // Pivot is the last element
        int pivot = array[end];

        // Mark it as pivot
        Bar bar = bars[end];
        Color oldColor = bar.getColor();
        bar.setColor(comparingColor);
        bar.draw(g);
        bs.show();

        int index = start - 1;
        for (int i = start; i < end; i++) {
            if (array[i] < pivot) {
                index++;
                swap(index, i);
                swapping++;
            }
            comp++;
            
        }

        bar.setColor(oldColor);
        bar.draw(g);
        bs.show();

        // Move pivot to correct location
        index++;
        swap(index, end);
        swapping++;

        return index;
    }

    /* MERGE SORT */
    public void mergeSort() {
        sortingInProgress = true;
        sortingThread = new Thread(() -> {
            if (!isCreated())
                return;

            // Warm-up phase
            Sort.mergeSort(array.clone());
            
            g = bs.getDrawGraphics();

            // Calculate elapsed time
            startTime = System.nanoTime();
            Sort.mergeSort(array.clone());
            time = System.nanoTime() - startTime;

            comp = swapping = 0;

            mergeSort(0, array.length - 1);
            
            if (stopRequested) {
                sortingInProgress = false;
                g.dispose();
                return;
            } else {
                finishAnimation();
            }
            
            g.dispose();
        });
        startSorting();// Start the thread
    }

    // Recursive mergeSort
    private void mergeSort(int left, int right) {
        if (stopRequested) {
            sortingInProgress = false;
            g.dispose();
            return;
        }
        
        if (left >= right)
            return;

        // Find the middle
        int middle = (right + left) / 2;

        // Sort the left half
        mergeSort(left, middle);

        // Sort the second half
        mergeSort(middle + 1, right);

        // Merge them
        merge(left, middle, right);
    }

    // Merge for mergeSort
    private void merge(int left, int middle, int right) {
        Color mergeColor = getBarColor(middle);

        // Number of items in the first half
        int n1 = middle - left + 1;
        int n2 = right - middle;  // Second half

        // Create array for those parts
        int[] leftArr = new int[n1];
        for (int i = 0; i < n1; i++)
            leftArr[i] = array[left + i];

        int[] rightArr = new int[n2];
        for (int i = 0; i < n2; i++)
            rightArr[i] = array[middle + i + 1];

        // Starting index
        int l = 0, r = 0, k = left;  // k: for the original array

        // Merge
        while (l < n1 && r < n2) {
            Bar bar = bars[k];
            bar.clear(g);
            if (leftArr[l] < rightArr[r]) {
                array[k] = leftArr[l];
                bar.setValue(leftArr[l]);
                l++;
            } else {
                array[k] = rightArr[r];
                bar.setValue(rightArr[r]);
                r++;
            }

            bar.setColor(mergeColor);
            colorBar(k, swappingColor);
            k++;
            comp++;
            swapping++;
            
            if (stopRequested) {
                sortingInProgress = false;
                g.dispose();
                return;
            }
        }

        // Add the remaining in the two arrays if there are any
        while (l < n1) {
            Bar bar = bars[k];
            bar.clear(g);

            array[k] = leftArr[l];
            bar.setValue(leftArr[l]);
            bar.setColor(mergeColor);
            colorBar(k, swappingColor);
            l++;
            k++;
            swapping++;
            
            if (stopRequested) {
                sortingInProgress = false;
                g.dispose();
                return;
            }
        }

        while (r < n2) {
            Bar bar = bars[k];
            bar.clear(g);

            array[k] = rightArr[r];
            bar.setValue(rightArr[r]);
            bar.setColor(mergeColor);
            colorBar(k, swappingColor);
            r++;
            k++;
            swapping++;
            
            if (stopRequested) {
                sortingInProgress = false;
                g.dispose();
                return;
            }
        }
    }

    // Swap 2 elements given 2 indexes
    private void swap(int i, int j) {
        // Swap the elements
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;

        // Clear the bar
        bars[i].clear(g);
        bars[j].clear(g);

        // Swap the drawings
        bars[j].setValue(bars[i].getValue());
        bars[i].setValue(temp);

        colorPair(i, j, swappingColor);
    }

    // Color 2 bars and delay for visualization
    private void colorPair(int i, int j, Color color) {
        Color color1 = bars[i].getColor(), color2 = bars[j].getColor();
        // Drawing
        bars[i].setColor(color);
        bars[i].draw(g);

        bars[j].setColor(color);
        bars[j].draw(g);

        bs.show();

        // Delay
        try {
            TimeUnit.MILLISECONDS.sleep(speed);
        } catch (Exception ex) {
        }

        // Put back to original color
        bars[i].setColor(color1);
        bars[i].draw(g);

        bars[j].setColor(color2);
        bars[j].draw(g);

        bs.show();
    }

    // Color a bar and delay for visualization
    private void colorBar(int index, Color color) {
        Bar bar = bars[index];
        Color oldColor = bar.getColor();

        bar.setColor(color);
        bar.draw(g);
        bs.show();

        try {
            TimeUnit.MILLISECONDS.sleep(speed);
        } catch (Exception ex) {
        }

        bar.setColor(oldColor);
        bar.draw(g);

        bs.show();
    }

    // Swiping effect when the sorting is finished
    private void finishAnimation() {
        // Swiping to green
        for (int i = 0; i < bars.length; i++) {
            colorBar(i, comparingColor);
            bars[i].setColor(getBarColor(i));
            bars[i].draw(g);
            bs.show();
        }

        // Show elapsed time and comparisons
        listener.onArraySorted(time, comp, swapping);
    }
    
    

    // For restoring purpose
    public void drawArray() {
        if (!hasArray)
            return;

        g = bs.getDrawGraphics();

        for (int i = 0; i < bars.length; i++) {
            bars[i].draw(g);
        }

        bs.show();
        g.dispose();
    }

    // Check if array is created
    private boolean isCreated() {
        if (!hasArray)
            JOptionPane.showMessageDialog(null, "You need to create an array!", "No Array Created Error",
                    JOptionPane.ERROR_MESSAGE);
        return hasArray;
    }

    // Setter for capacity
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // Setter for FPS
    public void setFPS(int fps) {
        this.speed = (int) (50000.0 / fps);
    }

    // Interface for handling sorting events
    public interface SortedListener {
        void onArraySorted(long elapsedTime, int comparison, int swapping);

        BufferStrategy getBufferStrategy();
    }
}
