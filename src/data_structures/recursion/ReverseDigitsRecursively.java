package data_structures.recursion;

public class ReverseDigitsRecursively {
    public static void main(String[] args) {
        int n = 12345;
        int rev = reverse(n, 0);
        System.out.println("Reversed number for "+n+" is "+rev);
    }

    private static int reverse(int n, int reversed) {
        if (n == 0) return reversed;
        int rem = n % 10 + reversed * 10;
        return reverse(n / 10, rem);

    }
}
