package data_structures.recursion;

public class SuperDigitRecursively {
    public static void main(String[] args) {
        String n1 = "3546630947312051453014172159647935984478824945973141333062252613718025688716704470547449723886626736";
        int k1 = 100000;
        String n = "9875";
        int k = 4;
        int x = superDigit(n1, k1);
        System.out.println(x);
    }

    public static int superDigit(String n, int k) {
        char[] arr = n.toCharArray();
        long sod = 0;
        //Add all digits iteratively for extremely large numbers
        for (int i = 0; i < arr.length; i++) {
            sod += arr[i] - '0';
        }
        return (int) superDigit(sod * k);
    }

    private static long superDigit(long n) {
        if (n / 10 == 0)
            return n;
        long sum = sumOfDigits(n, 0);
        return superDigit(sum);
    }

    private static long sumOfDigits(long n, long sum) {
        if (n == 0)
            return sum;
        sum += n % 10;
        return sumOfDigits(n / 10, sum);
    }
}
