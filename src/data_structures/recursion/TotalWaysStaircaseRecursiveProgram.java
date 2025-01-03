package data_structures.recursion;
import java.util.HashMap;

/*
 The staircase has 5 steps. Davis can step on the following sequences of steps:
1 1 1 1 1
1 1 1 2
1 1 2 1
1 2 1 1
2 1 1 1
1 2 2
2 2 1
2 1 2
1 1 3
1 3 1
3 1 1
2 3
3 2
There are 13 possible ways he can take these steps
* */
public class TotalWaysStaircaseRecursiveProgram {
    private static HashMap<Integer, Integer> memo = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(stepPerms(36));
    }

    public static int stepPerms(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int result = stepPerms(n - 1) + stepPerms(n - 2) + stepPerms(n - 3);
        memo.put(n, result);

        return result;
    }

}


