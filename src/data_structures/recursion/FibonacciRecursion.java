package data_structures.recursion;

public class FibonacciRecursion {
    public static void main(String[] args) {
        int res = returnFibonacci(4);
        System.out.println(res);
    }

    static int returnFibonacci(int n) {
        if (n < 2)
            return n;
        int left = returnFibonacci(n - 1);
        int right = returnFibonacci(n - 2);
        return left + right;
    }
}
