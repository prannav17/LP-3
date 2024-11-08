import java.util.Scanner;

public class    Knapsack {
    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    static int knapSack(int W, int wt[], int val[], int n) {
        int i, w;
        int K[][] = new int[n + 1][W + 1];

        for (i = 0; i <= n; i++) {
            for (w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (wt[i - 1] <= w)
                    K[i][w] = max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }
        return K[n][W];
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        int[] val = new int[n];
        int[] wt = new int[n];

        System.out.println("Enter the values of the items:");
        for (int i = 0; i < n; i++) {
            val[i] = scanner.nextInt();
        }

        System.out.println("Enter the weights of the items:");
        for (int i = 0; i < n; i++) {
            wt[i] = scanner.nextInt();
        }

        System.out.print("Enter the maximum weight capacity of the knapsack: ");
        int W = scanner.nextInt();

        System.out.println("Maximum value in Knapsack = " + knapSack(W, wt, val, n));

        scanner.close(); // Close the scanner
    }
}



//    Enter the number of items: 3
//        Enter the values of the items:
//        60
//        100
//        120
//        Enter the weights of the items:
//        10
//        20
//        30
//        Enter the maximum weight capacity of the knapsack: 50
//        Maximum value in Knapsack = 220
