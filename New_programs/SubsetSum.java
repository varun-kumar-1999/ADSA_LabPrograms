import java.util.*;
public class SubsetSum {
    public static void findSubsets(int[] set, int n, int sum, ArrayList<Integer> subset, int index) {
        if (sum == 0) {
            System.out.print("{ ");
            for (int i = 0; i < subset.size(); i++) {
                System.out.print(subset.get(i) + " ");
            }
            System.out.println("}");
            return;
        }

        if (index >= n || sum < 0) {
            return;
        }

        subset.add(set[index]);
        findSubsets(set, n, sum - set[index], subset, index + 1);

        subset.remove(subset.size() - 1);
        findSubsets(set, n, sum, subset, index + 1);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of set: ");
        int n = scanner.nextInt();
        int[] set = new int[n];
        System.out.println("Enter set elements in increasing order:");
        for (int i = 0; i < n; i++) {
            set[i] = scanner.nextInt();
        }

        System.out.print("Enter maximum limit: ");
        int maxLimit = scanner.nextInt();

        ArrayList<Integer> subset = new ArrayList<>();
        findSubsets(set, n, maxLimit, subset, 0);

        scanner.close();
    }
}
