import java.util.Scanner;
public class NQueens {

    static int[][] board;
    static int solutions;
    static void solveNQueens(int row, int n) {
        if (row == n) {
            solutions++;
            printBoard(n);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isSafe(row, col, n)) {
                board[row][col] = 1;
                solveNQueens(row + 1, n);
                board[row][col] = 0; // Backtrack
            }
        }
    }

    static boolean isSafe(int row, int col, int n) {
        // Check if there is a queen in the same column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }
        // Check upper-left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {     
            if (board[i][j] == 1) {
                return false;
            }
        }
        // Check upper-right diagonal
        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    static void printBoard(int n) {
        System.out.println("Solution " + solutions + ":");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] == 1 ? "Q " : ". ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of Queens: ");
        int n = scanner.nextInt();
        scanner.close();

        board = new int[n][n];
        solutions = 0;
        solveNQueens(0, n);
        System.out.println("Number of solutions: " + solutions);
    }
}
//T.C - O(n!)  S.C - O(n)