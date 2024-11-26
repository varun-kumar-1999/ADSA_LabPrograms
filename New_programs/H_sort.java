import java.util.*;
import java.io.*;
import java.lang.*;
public class H_sort {
    void sort(int arr[]) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // One by one extract an element from the heap
        for (int i = n - 1; i > 0; i--) {
            // Move the current root to the end
            //(NO BUILT-IN SWAP METHOD IN JAVAOo)
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    void heapify(int arr[], int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    static int[] generateRandomArray(int n) {
        int[] arr = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(100) + (-25) + 1; // Adjust the upper bound as needed
        }

        return arr;
    }


    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements (n): ");
        int n = scanner.nextInt();

        int[] arr = generateRandomArray(n);

        System.out.println("Original array:");
        printArray(arr);

        long startTime = System.currentTimeMillis();

        H_sort heapSort = new H_sort();
        heapSort.sort(arr);

        long endTime = System.currentTimeMillis();
        System.out.println("\nSorted array:");
        printArray(arr);

        //long tt = et - st;
        long totalTime = endTime - startTime;
        System.out.println("\nTotal time taken: " + totalTime + " milliseconds");
        scanner.close();
    }

}
