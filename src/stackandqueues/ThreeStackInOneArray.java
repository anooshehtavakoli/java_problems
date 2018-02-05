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
        /* we can divide the array in three equal parts and allow the individual stack to grow in that limited space.
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






    public class MultiStack {
        /* A second approach is to allow the stack blocks to be flexible in size.
        when one stack exceeds its initial capacity, we grow the allowable capacity and shift elements as necessary.
        we will also design our array to be circular, such that the final stack may start at the end of the array
        and wrap around to the beginning.
         */

        /*stackInfo is a simple class that holds a set of data about each stack. It does not hold the actual
        items in the stack. we could have done this with just a bunch of individual variables, but that's messy
        and doesn't gain us much.
         */

        private class StackInfo {
            public int start, size, capacity;

            public StackInfo(int start, int capacity) {
                this.start = start;
                this.capacity = capacity;
            }

            /* check if an index on the full array is within the stack bounderies.
                the stack can wrap around to the start of the array. */
            public boolean isWithinStackCapacity(int index) {
                // if outside of bounds of array, return false.
                if (index < 0 || index >= values.length) {
                    return false;
                }

                // if index wraps around, adjust it.
                int contiguousIndex = index < start ? index + values.length : index;
                int end = start + capacity;
                return start <= contiguousIndex && contiguousIndex < end;
            }

            public int lastCapacityIndex() {
                return adjustIndex(start + capacity - 1);
            }

            public int lastElementIndex() {
                return adjustIndex(start + size - 1);
            }

            public boolean isFull() { return size == capacity; }
            public boolean isEmpty() { return size == 0; }
        }

        private StackInfo[] info;
        private int[] values;

        public MultiStack(int numberOfStacks, int defaultSize) {
            // Create metadata for all the stacks.
            info = new StackInfo[numberOfStacks];
            for (int i = 0; i < numberOfStacks; i++) {
                info[i] = new StackInfo(defaultSize * i, defaultSize);
            }
            values = new int[numberOfStacks * defaultSize];
        }

        // push value onto stack num, shifting/expanding stacks as necessary, throws exception if all stacks are full.
        public void push(int stackNum, int value) throws FullStackException {
            if (allStacksAreFull()) {
                throw new FullStackException();
            }

            // If this stack is full, expand it.
            StackInfo stack = info[stackNum];
            if (stack.isFull()) {
                expand(stackNum);
            }

            // Find the index of the top element in the array + 1, and increment the stack pointer.
            stack.size++;
            values[stack.lastElementIndex()] = value;
        }

        // Remove value from stack.
        public int pop(int stackNum) throws Exception {
            StackInfo stack = info[stackNum];
            if (stack.isEmpty()) {
                throw new EmptyStackException();
            }

            // Remove last element
            int value = values[stack.lastElementIndex()] = 0; // clear item
            stack.size--; // Shrink size
            return value;
        }

        // Get top element of stack.
        public int peek(int stackNum) {
            StackInfo stack = info[stackNum];
            return values[stack.lastElementIndex()];
        }

        /* Shift items in stack over by one element. If we have available capacity, then we will end up
           shrinking the stack by one element. If we don't have available capacity,
           then we will need to shift the next stack over too. */
        private void shift(int stackNum) {
            System.out.println("/// Shifting " + stackNum);
            StackInfo stack = info[stackNum];

            /* If this is at its full capacity, then you need to move the next stack over by one element.
               This stack can now claim the freed index. */
            if (stack.size >= stack.capacity) {
                int nextStack = (stackNum + 1) % info.length;
                shift(nextStack);
                stack.capacity++; // Claim index that next stack lost
            }

            // Shift all elements in stack over by one.
            int index = stack.lastCapacityIndex();
            while (stack.isWithinStackCapacity(index)) {
                values[index] = values[previousIndex(index)];
                index = previousIndex(index);
            }

            // Adjust stack data.
            values[stack.start] = 0; // Clear item.
            stack.start = nextIndex(stack.start); // move start
            stack.capacity--;  // Shrink capacity
        }

        // Expand stack by shifting over other stacks.
        private void expand(int stackNum) {
            shift((stackNum + 1) % info.length);
            info[stackNum].capacity++;
        }

        // Returns the number of items actually present in stack
        public int numberOfElements() {
            int size = 0;
            for (StackInfo sd : info) {
                size += sd.size;
            }
            return size;
        }

        // Return true is all the stacks are full.
        public boolean allStacksAreFull() {
            return numberOfElements() == values.length;
        }

        // Adjust index to be within the range of 0 -> 1.
        private int adjustIndex(int index) {
            /* Java's mod operator can return neg values. for example, (-11 % 5) will return -1, not 4.
               we actually want the values to be 4 (since we're wrapping around the index).
             */
            int max = values.length;
            return ((index % max) + max) % max;
        }

        // Get index after this index, adjusted for wrap around.
        private int nextIndex(int index) {
            return adjustIndex(index + 1);
        }

        // Get index before this index, adjusted for wrap around.
        private int previousIndex(int index) {
            return adjustIndex(index - 1);
        }
    }

    private class FullStackException extends Exception {

    }
}
