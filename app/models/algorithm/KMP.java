package models.algorithm;

/**
 * Created with IntelliJ IDEA.
 * User: adinata
 * Date: 11/28/13
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class KMP implements IAlgorithm {
    @Override
    public int countWord(String text, String pattern) {
        return searchSubString(text.toCharArray(), pattern.toCharArray());
    }
    /**
     * Pre processes the pattern array based on proper prefixes and proper
     * suffixes at every position of the array
     *
     * @param ptrn
     *            word that is to be searched in the search string
     * @return partial match table which indicates
     */
    public int[] preProcessPattern(char[] ptrn) {
        int i = 0, j = -1;
        int ptrnLen = ptrn.length;
        int[] b = new int[ptrnLen + 1];

        b[i] = j;
        while (i < ptrnLen) {
            while (j >= 0 && ptrn[i] != ptrn[j]) {
                // if there is mismatch consider next widest border
                j = b[j];
            }
            i++;
            j++;
            b[i] = j;
        }
        // print pettern, partial match table and index
        return b;
    }

    /**
     * Based on the pre processed array, search for the pattern in the text
     *
     * @param text
     *            text over which search happens
     * @param ptrn
     *            pattern that is to be searched
     */
    public int searchSubString(char[] text, char[] ptrn) {
        int i = 0, j = 0;
        // pattern and text lengths
        int ptrnLen = ptrn.length;
        int txtLen = text.length;

        // initialize new array and preprocess the pattern
        int[] b = preProcessPattern(ptrn);
        int ret = 0;
        while (i < txtLen) {
            while (j >= 0 && text[i] != ptrn[j]) {
                j = b[j];
            }
            i++;
            j++;

            // a match is found
            if (j == ptrnLen) {
                ret++;
                j = b[j];
            }
        }
        return ret;
    }

    // only for test purposes
    public static void main(String[] args) {
        KMP stm = new KMP();
        // pattern
        char[] ptrn = "abcabdabc".toCharArray();

        char[] text = "abcabdabcabeabcabdabcabd".toCharArray();
        System.out.print(" ");
        for (char c : text) {
            System.out.print(c + "   ");
        }
        System.out.println(stm.searchSubString(text, ptrn));
        // search for pattern in the string

    }
}
