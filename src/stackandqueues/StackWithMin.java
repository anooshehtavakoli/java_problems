package stackandqueues;

import java.util.Stack;

/**
 *
 * 3.2 How would you design a stack which, in addition to push and pop, has a function min
 * which returns the minimum element? Push, Pop and min should all operate in O(1) time.
 *
 *
 * Created by anoosheh on 1/8/18.
 */
public class StackWithMin extends Stack<NodeWithMin> {
    public void push(int value) {
        int newMin = Math.min(value, min());
        super.push(new NodeWithMin(value, newMin));
    }

    public int min() {
        if (this.isEmpty()) {
            return Integer.MAX_VALUE;  // Error value
        } else {
            return peek().min;
        }
    }
}

class NodeWithMin {
    public int value;
    public int min;
    public NodeWithMin(int v, int min) {
        value = v;
        this.min = min;
    }
}

// there is just one issue with this, if we have a large stack, we waste a lot of space
// by keeping track of the min for every single element. Can we do better?
// We can (maybe) do a bit better than this by using an additional stack which keeps track of the mins.

class StackWithMin2 extends Stack<Integer> {
    Stack<Integer> s2;
    public StackWithMin2() {
        s2 = new Stack<Integer>();
    }

    public void push(int value) {
        if (value <= min()) {
            s2.push(value);
        }
        super.push(value);
    }

    public Integer pop() {
        int value = super.pop();
        if (value == min()) {
            s2.pop();
        }
        return value;
    }

    public int min() {
        if (s2.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return s2.peek();
        }
    }
}
