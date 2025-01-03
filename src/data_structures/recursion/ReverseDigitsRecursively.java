package data_structures.recursion;

public class ReverseDigitsRecursively {

    public static void main(String[] args) {
        int n = 12345;
        int rev = reverse(n, 0);
        System.out.println("Reversed number for " + n + " is " + rev);

        int num2 = 1234;
        System.out.println(reverseWithBase(num2, (int) Math.log10(num2)));
    }

    //In this scenario, the reverse number itself keeps updating with each recursive call stack and is returned at the end
    private static int reverse(int n, int reversed) {
        if (n == 0) return reversed;
        int rem = n % 10 + reversed * 10;
        return reverse(n / 10, rem);
    }

    private static int reverseWithBase(int n, int power) {
        if (n % 10 == n)
            return n;
        int rem = n % 10;
        return rem * (int) Math.pow(10, power ) + reverseWithBase(n / 10, power - 1);
    }
}
