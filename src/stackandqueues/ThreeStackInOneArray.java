package stackandqueues;

import java.util.EmptyStackException;

/**
 *
 * 3.1 Describe how you could use a single array to implement three atacks.
 * Approach 1:
 * like many problems, this one somewhat depends on
 * how well we'd like to support these stacks.
 * if we're okay with simply allocating a fixes amount of space
 * for each stack, we can do that.
 * This may mean though that one stack runs out of space,
 * while the others are nearly empty.
 *
 * Approach 2:
 * Alternatively, we can be flexible in our space allocation,
 * but this significantly increase the complexity of the problem.
 *
 *
 * Created by anoosheh on 1/7/18.
 */
public class ThreeStackInOneArray {
    class FixedMultiStack {
        /* we can divide the array in three equal parts and allow the indicidual atack to grow in that limited space.
        Note: we will use the notation "[" to mean inclusive of an end point and "(" to mean exclusive of an end point.
         for stack 1, we will use [0, n/3)
         for stack 2, we will use [n/3, 2n/3)
         for stack 3, we will use [2n/3, n)
         */
        private int numberOfStacks = 3;
        private int stackCapacity;
        private int[] values;
        private int[] sizes;

        public FixedMultiStack(int stackSize) {
            stackCapacity = stackSize;
            values = new int[stackSize * numberOfStacks];
            sizes = new int[numberOfStacks];
        }

        // push value onto stack
        public void push(int stackNum, int value) throws FullStackException {
            // Check that we have space for the next element
            if(isFull(stackNum)) {
                throw new FullStackException();
            }

            // Increment stack pointer and then update top value.
            sizes[stackNum]++;
            values[indexOfTop(stackNum)] = value;
        }


        // Pop item from top stack.
        public int pop(int stackNum) {
            if (isEmpty(stackNum)) {
                throw new EmptyStackException();
            }

            int topIndex = indexOfTop(stackNum);
            int value = values[topIndex]; //Get top
            values[topIndex] = 0; // Clear
            sizes[stackNum]--; //Shrink
            return value;
        }

        // Return top element.
        public int peek(int stackNum) {
            if (isEmpty(stackNum)) {
                throw new EmptyStackException();
            }
            return values[indexOfTop(stackNum)];
        }

        // Return if stack is empty.
        public boolean isEmpty(int stackNum) {
            return sizes[stackNum] == 0;
        }

        // Return if stack is full.
        public boolean isFull(int stackNum) {
            return sizes[stackNum] == stackCapacity;
        }

        // return index of the top of the stack.
        private int indexOfTop(int stackNum) {
            int offset = stackNum * stackCapacity;
            int size = sizes[stackNum];
            return offset + size -1;
        }
    }

    private class FullStackException extends Exception {

    }
}
