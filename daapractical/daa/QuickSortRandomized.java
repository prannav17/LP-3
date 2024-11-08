import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class QuickSortRandomized {

    public static int randomizedPartition(int[] arr, int low, int high) {
        Random rand = new Random();
        int randomPivot = rand.nextInt(high - low + 1) + low;
        int temp = arr[randomPivot];
        arr[randomPivot] = arr[high];
        arr[high] = temp;
        return partition(arr, low, high);
    }


    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }


    public static void quicksortRandomized(int[] arr, int low, int high) {
        if (low < high) {
            int pi = randomizedPartition(arr, low, high);
            quicksortRandomized(arr, low, pi - 1);
            quicksortRandomized(arr, pi + 1, high);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter the size of the array: ");
        int n = scanner.nextInt();


        int[] arr = new int[n];


        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Measure the time taken to sort the array
        long startTime = System.nanoTime();
        quicksortRandomized(arr, 0, arr.length - 1);
        long endTime = System.nanoTime();

        // Output the sorted array and time taken
        System.out.println("Sorted array: " + Arrays.toString(arr));
        System.out.println("Randomized QuickSort Time: " + (endTime - startTime) + " ns");

        scanner.close(); // Close the scanner
    }
}




//    Enter the size of the array: 7
//        Enter 7 integers:
//        10
//        80
//        30
//        90
//        40
//        50
//        70
//        Sorted array: [10, 30, 40, 50, 70, 80, 90]
//        Randomized QuickSort Time: 12345 ns
