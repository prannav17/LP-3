import java.util.Scanner;

public class NQueens {
    private static int N;
    private static int[][] board;

    static boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    static boolean solveNQueens(int row) {
        if (row >= N) {
            printBoard();
            return true;
        }

        boolean res = false;
        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                // Place the queen
                board[row][col] = 1;

                res = solveNQueens(row + 1) || res;

                board[row][col] = 0;
            }
        }

        return res;
    }

    static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the board (N): ");
        N = scanner.nextInt();

        board = new int[N][N];

        if (!solveNQueens(0)) {
            System.out.println("No solution exists");
        }
    }
}





//    Enter the size of the board (N): 4
//        1 0 0 0
//        0 0 1 0
//        0 0 0 1
//        0 1 0 0
//
//        1 0 0 0
//        0 1 0 0
//        0 0 0 1
//        0 0 1 0
//


