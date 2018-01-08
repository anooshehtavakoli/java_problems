package arraysandstrings;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Given two strings, write a method to decide if one is permutation of the other.
 *
 * Created by anoosheh on 12/21/17.
 */
public class Permutation {

    @Test
    public void testPermutation() {
        boolean isPermutation = permutation("anoosheh", "noosheah");
        Assert.assertTrue(isPermutation);
    }

    @Test
    public void testNotPermutation() {
        boolean notPermutation = permutation("anoosheh", "something");
        Assert.assertFalse(notPermutation);
    }

    public static String sort(String string) {
        char[] content = string.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }

    public static boolean permutation(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        String sortedS = sort(s);
        String sortedT = sort(t);
        return sortedS.equals(sortedT);
    }
}
