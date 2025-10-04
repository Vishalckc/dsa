package data_structures.patterns;
//https://static.takeuforward.org/wp/uploads/2022/08/P5.png
/*---------------
 *****
 ****
 ***
 **
 *
 *
 ------------
* */
public class Pattern3 {
    public static void main(String[] args) {
        for (int i = 5; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
