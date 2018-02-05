package bitmanipulation;

/**
 * Created by anoosheh on 1/24/18.
 */
public class LogicalRightShift {
    int repeatedLogicalShift(int x, int count) {
        for (int i = 0; i < count; i++) {
            x >>>= 1; // Logical shift by 1
        }
        return x;
    }

    public static void main(String[] args) {
        LogicalRightShift logical = new LogicalRightShift();
       logical.repeatedLogicalShift(-93242, 40);
    }
}
