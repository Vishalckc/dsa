package tdd.codekatas.decode.java;

//See readme.md for problem statement
public class WordDecoder {
    public String decodeWord(String input) {
        if (input == null || input.trim().isEmpty())
            throw new IllegalArgumentException("Input cannot be null or empty");
        //get First character ascii value
        int firstCharAsciiValue = input.charAt(0);
        //check if first character is 'a' and input length is 1
        if (input.length() == 1) {
            if (firstCharAsciiValue == Character.valueOf('a'))
                return Character.toString('z');
            return Character.toString((char) (firstCharAsciiValue - 1));
        }
        int[] asciiArray = new int[input.length()];
        asciiArray[0] = firstCharAsciiValue;
        // For each consecutive character, evaluate
        for (int i = 1; i < input.length(); i++) {
            asciiArray[i] = asciiArray[i - 1] + getNextAsciiValue(input.charAt(i - 1), input.charAt(i));
        }
        int temp = asciiArray[0];
        int temp2 = temp;
        for (int i = 1; i < asciiArray.length; i++) {
            temp2 = asciiArray[i];
            asciiArray[i] = temp2 - temp;
            temp = temp2;
        }
        asciiArray[0] = asciiArray[0] == 96 ? 122 : asciiArray[0] - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < asciiArray.length; i++) {
            sb.append((char) asciiArray[i]);
        }
        return sb.toString();
    }

    private int getNextAsciiValue(int prev, int current) {
        int diff = current - prev;//111-110=1
        while (diff < 97 || diff > 122) {
            diff += 26;//26*4+1=105
        }
        return diff;
    }

}
