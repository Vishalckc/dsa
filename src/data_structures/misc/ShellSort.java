package data_structures.misc;

public class ShellSort {
    public static void main(String[] args) {
        long c = 0l;
        int arr[] = {329, 3, -91, 33, 67, 42, 39, 71, 83, 173};
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            System.out.println(" gap " + gap);
            c++;
            int i;
            for (i = gap; i < arr.length; i++) {
                int element = arr[i];
                c++;
                System.out.println(" element " + element);

                int j;
                for (j = i; j >= gap && arr[j - gap] > element; j -= gap) {
                    arr[j] = arr[j - gap];
                    c++;
                }
                arr[j] = element;

                System.out.println(arr[j] + " pos " + j);
            }
        }
        System.out.println("--------PRINTING SORTED--------------");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println("count computations " + c);
    }
}
