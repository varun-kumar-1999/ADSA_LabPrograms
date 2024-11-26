import java.util.Random;
import java.util.Scanner;

public class Q_sort {
    void sort(int arr[], int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            // Recursively sort elements before and after partition
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;

                // Swap arr[i] and arr[j]  swap() won't work works on passby value not pass by refrence
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i+1] and arr[high] (pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    static int[] generateRandomArray(int n) {
        int[] arr = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(100)+(-25)+1; // Adjust the upper bound as needed
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

        Q_sort quickSort = new Q_sort();
        quickSort.sort(arr, 0, n - 1);

        long endTime = System.currentTimeMillis();

        System.out.println("\nSorted array:");
        printArray(arr);

        long totalTime = endTime - startTime;
        System.out.println("\nTotal time taken: " + totalTime + " milliseconds");
        scanner.close();
    }
}

