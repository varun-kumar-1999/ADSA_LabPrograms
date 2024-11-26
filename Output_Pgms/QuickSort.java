import java.util.*;
public class QuickSort {

    static Integer[] generateRandomArray(int n) {
        Integer[] arr = new Integer[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(100) + (-25) + 1; // Adjust the upper bound as needed
        }

        return arr;
    }

    static void printArray(Integer arr[]) {
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
	    Integer[] arr = generateRandomArray(n);

        System.out.println("Original array:");
        printArray(arr);

        long startTime = System.currentTimeMillis();

        Arrays.sort(arr);

        long endTime = System.currentTimeMillis();
        System.out.println("\nSorted array:");
        printArray(arr);


        long totalTime = endTime - startTime;
        System.out.println("\nTotal time taken: " + totalTime + " milliseconds");
        scanner.close();
    }

}