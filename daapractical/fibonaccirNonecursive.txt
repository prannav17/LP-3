import java.util.Scanner;

public class FibonacciNonRecursive {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter the number of Fibonacci numbers to calculate: ");
        int n = scanner.nextInt();


        if (n <= 1) {
            System.out.println("Please enter a number greater than 1.");
            return;
        }


        int[] fib = new int[n];
        fib[0] = 0;
        fib[1] = 1;


        for (int i = 2; i < n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }


        System.out.println("The first " + n + " Fibonacci numbers are:");
        for (int i = 0; i < n; i++) {
            System.out.print(fib[i] + " ");
        }
    }
}



//public class FibonacciRecursive {
//    public static void main(String[] args) {
//        int n = 10; // Example: Calculate the first 10 Fibonacci numbers
//        for (int i = 0; i < n; i++) {
//            System.out.print(fibonacci(i) + " ");
//        }
//    }
//    public static int fibonacci(int n) {
//        if (n <= 1) {
//            return n;
//        }
//        return fibonacci(n - 1) + fibonacci(n - 2);
//    }