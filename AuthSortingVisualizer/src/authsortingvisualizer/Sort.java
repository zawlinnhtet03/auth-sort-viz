package authsortingvisualizer;

// Sort algorithms
public class Sort {
    // Selection Sort algorithm
    public static <T extends Comparable<T>> void selectionSort(T[] data) {
        for (int i = 0; i < data.length; i++) {
            int min = i;

            for (int scan = i; scan < data.length; scan++)
                if (data[scan].compareTo(data[min]) < 0)
                    min = scan;

            swap(data, i, min);
        }
    }

    // Insertion Sort algorithm
    public static <T extends Comparable<T>> void insertionSort(T[] data) {
        for (int i = 1; i < data.length; i++) {
            T current = data[i];
            int position = i;

            while (position > 0 && data[position - 1].compareTo(current) > 0) {
                data[position] = data[position - 1];
                position--;
            }

            data[position] = current;
        }
    }

    // Bubble Sort algorithm
    public static <T extends Comparable<T>> void bubbleSort(T[] data) {
        int position, scan;

        for (position = data.length - 1; position > 0; position--) {
            for (scan = 0; scan < position; scan++) {
                if (data[scan].compareTo(data[scan + 1]) > 0)
                    swap(data, scan, scan + 1);
            }
        }
    }

    // Quick Sort algorithm
    public static <T extends Comparable<T>> void quickSort(T[] data) {
        quickSort(data, 0, data.length - 1);
    }

    // Recursive helper method for Quick Sort
    private static <T extends Comparable<T>> void quickSort(T[] data, int start, int end) {
        if (start < end) {
            int middle = partition(data, start, end);

            quickSort(data, start, middle - 1);
            quickSort(data, middle + 1, end);
        }
    }

    // Partition method for Quick Sort
    private static <T extends Comparable<T>> int partition(T[] data, int start, int end) {
        int pivot = (start + end) / 2;
        int left, right;
        T pivotElem = data[pivot];

        swap(data, pivot, start);

        left = start + 1;
        right = end;

        while (left < right) {
            while (left < right && data[left].compareTo(pivotElem) <= 0)
                left++;

            while (data[right].compareTo(pivotElem) > 0)
                right--;

            if (left < right)
                swap(data, left, right);
        }

        swap(data, start, right);

        return right;
    }

    // Merge Sort algorithm
    public static <T extends Comparable<T>> void mergeSort(T[] data) {
        mergeSort(data, 0, data.length - 1);
    }

    // Recursive helper method for Merge Sort
    private static <T extends Comparable<T>> void mergeSort(T[] data, int start, int end) {
        if (start < end) {
            int middle = (start + end) / 2;

            mergeSort(data, start, middle);
            mergeSort(data, middle + 1, end);

            merge(data, start, middle, end);
        }
    }

    // Merge method for Merge Sort
    private static <T extends Comparable<T>> void merge(T[] data, int start, int middle, int end) {
        T[] temp = (T[]) (new Comparable[end - start + 1]);
        int left = start, right = middle + 1;
        int index = 0;

        while (left <= middle && right <= end) {
            if (data[left].compareTo(data[right]) < 0) {
                temp[index] = data[left];
                left++;
            } else {
                temp[index] = data[right];
                right++;
            }

            index++;
        }

        if (left <= middle)
            for (int i = 0; i <= middle - left; i++)
                temp[index + i] = data[left + i];

        if (right <= end)
            for (int i = 0; i <= end - right; i++)
                temp[index + i] = data[right + i];

        for (int i = 0; i <= end - start; i++)
            data[start + i] = temp[i];
    }

    // Swap elements in the array
    private static <T extends Comparable<T>> void swap(T[] data, int element1, int element2) {
        T temp = data[element2];
        data[element2] = data[element1];
        data[element1] = temp;
    }
}
