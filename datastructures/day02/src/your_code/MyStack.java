package your_code;
import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;

    public MyStack() {
        ll = new LinkedList<>();
    }

    @Override
    public void push(Integer e) {
        ll.addFirst(e);
    }

    @Override
    public Integer pop() {
        Integer pop = ll.removeFirst();
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() { // Returns the largest element currently on the stack
        if (ll.peek() != null) {
            int maxnum;
            LinkedList<Integer> stack = new LinkedList<>();

            maxnum = ll.peek();

            while (ll.peek() != null) { // While stack is not empty
                Integer pop = ll.pop();
                stack.add(pop); // Keep track of removed items
                if (pop > maxnum) {
                    maxnum = pop;
                }
            }
            ll = stack;
            return maxnum;
        } else {
            return null;
        }
    }
}
