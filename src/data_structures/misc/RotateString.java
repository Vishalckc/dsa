package data_structures.misc;

public class RotateString {
    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "acbde";
        System.out.println(rotateString(s1, s2));
    }

    public static boolean rotateString(String s, String goal) {
        for (int i = 0; i < s.length(); i++) {
            String first = String.valueOf(s.charAt(0));
            String last = s.substring(1);
            s = last + first;
            System.out.println(s);
            if (s.equals(goal)) {
                return true;
            }
        }
        return false;
    }
}
