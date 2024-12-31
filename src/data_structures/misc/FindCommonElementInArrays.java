package data_structures.misc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class FindCommonElementInArrays {
    public static void main(String[] args) {
        int a[] = {1, 2, 3, 4, 7};
        int b[] = {5, 6, 9, 8};
        boolean res = findCommon(a, b);
        System.out.println(res);
    }

    static boolean findCommon(int a[], int b[]) {
        Set<Integer> set = new HashSet<Integer>();
        AtomicBoolean flag= new AtomicBoolean(false);
        for (int i = 0; i < a.length; i++) {
            set.add(a[i]);
        }

       for (int j=0;j<b.length;j++)
       {
           if(set.contains(b[j])){
               return true;
           }
       }
       return false;
    }
}
