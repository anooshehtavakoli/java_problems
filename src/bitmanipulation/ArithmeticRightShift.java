package bitmanipulation;

/**
 * Created by anoosheh on 1/24/18.
 */
public class ArithmeticRightShift {
    int repeatArithmeticShift(int x, int count) {
        for (int i = 0; i < count; i ++) {
            x >>= 1;   // Arithmetic shift by 1
        }
        return x;
    }


    public static void main(String[] args) {
        ArithmeticRightShift arithmetic = new ArithmeticRightShift();
        arithmetic.repeatArithmeticShift(-93242, 40);
    }
}
