package bitmanipulation;

/**
 * Created by anoosheh on 1/24/18.
 */
public class BitTasks {
    // Get Bit
    boolean getBit(int num, int i) {
        return ((num & (1 << i)) != 0);
    }

    // Set Bit
    int setBit(int num, int i) {
        return num | (1 << i);
    }

    // Clear Bit
    int clearBit(int num, int i) {
        int mask = ~(1 << i);
        return num & mask;
    }

    int clearBitsMSBthroughI(int num, int i) {
        int mask = (1 << i) - 1;
        return num & mask;
    }

    int clearBitsIthrough0(int num, int i) {
        int mask = ( -1 << (i + 1));
        return num & mask;
    }

    // Update Bit
    int updateBit(int num, int i, boolean bitIs1) {
        int value = bitIs1 ? 1 : 0;
        int mask = ~(1 << i);
        return (num & mask) | (value << i);
    }

}
