import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Item {
    int value, weight;

    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

public class FractionalKnapsack {
    private static double getMaxValue(Item[] items, int capacity) {
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                double r1 = (double) o1.value / o1.weight;
                double r2 = (double) o2.value / o2.weight;
                return Double.compare(r2, r1);
            }
        });

        double totalValue = 0;

        for (Item item : items) {
            if (capacity - item.weight >= 0) {
                capacity -= item.weight;
                totalValue += item.value;
            } else {
                totalValue += item.value * ((double) capacity / item.weight);
                break;
            }
        }
        return totalValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        Item[] items = new Item[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter value and weight for item " + (i + 1) + ": ");
            int value = scanner.nextInt();
            int weight = scanner.nextInt();
            items[i] = new Item(value, weight);
        }

        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = scanner.nextInt();

        double maxValue = getMaxValue(items, capacity);
        System.out.println("Maximum value we can obtain = " + maxValue);
    }
}


//    Enter the number of items: 3
//        Enter value and weight for item 1: 60 10
//        Enter value and weight for item 2: 100 20
//        Enter value and weight for item 3: 120 30
//        Enter the capacity of the knapsack: 50
//        Maximum value we can obtain = 240.0
