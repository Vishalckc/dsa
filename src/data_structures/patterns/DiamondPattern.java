/**
 * Prints a diamond pattern.
 *
 * The pattern is a triangle with increasing and then decreasing number of stars on each row.
 * The number of rows is given by {@code MAX_STARS}.
 *
 * Example:
 * <pre>
 *   *
 *  ***
 * *****
 *  ***
 *   *
 * </pre>
 */
package data_structures.patterns;

public class DiamondPattern {
    /**
     * The maximum number of rows in the diamond pattern.
     */
    public static final int MAX_STARS = 99;

    /**
     * Prints the diamond pattern.
     * @param args command-line arguments (ignored)
     */
    public static void main(String[] args) {
        int spaces, stars;
        spaces = MAX_STARS / 2;
        for (int i = 1; i <= (MAX_STARS / 2) + 1; i++) {
            //Increasing half
            for (int j = 1; j <= spaces; j++) {
                System.out.print(" ");
            }
            stars = getStars(spaces);
            for (int j = 1; j <= stars; j++) {
                System.out.print("*");
            }
            for (int j = 1; j <= spaces; j++) {
                System.out.print(" ");
            }
            spaces--;
            System.out.println();

        }
        //Decreasing half
        spaces = 1;
        for (int i = MAX_STARS - (MAX_STARS / 2); i > 0; i--) {
            for (int j = 1; j <= spaces; j++) {
                System.out.print(" ");
            }
            stars = getStars(spaces);
            for (int j = 1; j <= stars; j++) {
                System.out.print("*");
            }
            for (int j = 1; j <= spaces; j++) {
                System.out.print(" ");
            }
            spaces++;
            System.out.println();
        }

    }

    /**
     * Returns the number of stars in a row of the diamond pattern.
     * @param spaces the number of spaces before and after the starts in each row
     * @return the number of stars in a row
     */
    private static int getStars(int spaces) {
        return MAX_STARS - 2 * spaces;
    }
}