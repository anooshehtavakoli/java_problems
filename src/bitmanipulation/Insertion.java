package bitmanipulation;

/**
 *
 * 5.1: You are given two 32-bit numbers, N and M, and two bit positions, i and j. write a method to insert M into N
 * such that M starts at bit j and ends at bit i. you can assume that the bits J through i have enough space to fit
 * all of M. That is, if M = 10011, you can assume that there are at least 5 bits between J and i. you would not,
 * for example, hace j = 3 and i = 2, because M could not fully fit between bit 3 and bit 2.
 * EXAMPLE:
 * Input  N = 10000000000, M = 10011, i = 2, j = 6
 * Output: N = 10001001100
 *
 * Created by anoosheh on 1/29/18.
 */
public class Insertion {
    /*
    This problem can be approached in three key steps:
    1: Clear the bits J through i in N
    2: Shift M so that it lines up with bits j through i
    3: Merge M and N

    The trickiest part is step 1. How do we clear the bits in N? We can do this with a mask. This mask will have all 1s,
    except for )s in the bits j through i. We create this by creating the left half of the mask first, and then the right half.
     */

    int updateBits(int n, int m, int i, int j) {
        /* Create a mask to clear bits i through j in N. Example: i = 2, j = 4. Result should be 11100011. For simplicity,
        we'll use just 8 bits for the example. */
        int allOnes = ~0;   // will equal sequence of all 1s

        // 1s before position j, then 0s. left = 11100000
        int left = allOnes << (j + 1);

        // 1's after position i. right = 00000011
        int right = ((1 << i) - 1);

        // All 1s, except for 0s between i and j. mask = 11100011
        int mask = left | right;

        /* clear bits j through i then put m in there */
        int n_cleared = n & mask;  // Clear bits j through i.
        int m_shifted = m << i;  // Move m into correct position.

        return n_cleared | m_shifted;  // OR them, and we're done
    }



}
