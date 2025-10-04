package data_structures.recursion;

import com.sun.tools.javac.util.List;

import java.util.Stack;

public class removeInvalidParantheses {
    public static void main(String[] args) {
        // String str = "()())()(";
        String str = "(a)())()";
        List<String> validStr = removeInvalid(str);
        validStr.stream().forEach(System.out::println);
    }

    private static List<String> removeInvalid(String str) {
        Stack<Character> stack = new Stack<>();
        int[] index = new int[str.length()];
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(str.charAt(i));
            } else if (!stack.isEmpty() && str.charAt(i) == ')' && stack.peek() == '(') {
                stack.pop();
            } else if (str.charAt(i) == ')') {
                stack.push(str.charAt(i));
                index[count++] = i;
            } else if (!stack.isEmpty() && stack.peek() == ')') {
                index[count++] = i;
            }
        }
        System.out.println(stack.size());
        char[] ch = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            if (index[i] != i) {
                ch[i] = str.charAt(i);
            }
        }
        return List.of(new String(ch));
    }
}
