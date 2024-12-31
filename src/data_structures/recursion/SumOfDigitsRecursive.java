package data_structures.recursion;

public class SumOfDigitsRecursive {
    public static void main(String[] args) {
        int sum = sum(54321);
        System.out.println(sum);
    }

    private static int sum(int n) {
        if (n / 10 == 0)
            return n;
        return n % 10 + sum(n / 10);
    }
}
