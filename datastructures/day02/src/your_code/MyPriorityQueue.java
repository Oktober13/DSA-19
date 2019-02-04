package your_code;

import java.util.LinkedList;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue<T> {

    private LinkedList<Integer> ll;

    public MyPriorityQueue() {
        ll = new LinkedList<>();
    }

//    public boolean isEmpty() {
//        return ll.isEmpty();
//    }

    public void enqueue(int item) { // Adds item to queue.
        ll.add(item);
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        if (ll.size() == 1) {

            return ll.get(0);

        } else if (ll.size() > 1) {

            int max = ll.peek();
            int maxindex = 0;

            for (int i = 0; i < ll.size(); i++) {
                int curr = ll.get(i);
                if (curr >= max) {
                    max = curr;
                    maxindex = i;
                }
            }

            ll.remove(maxindex);
            return max;

        } else {

            return -1;

        }
    }

}