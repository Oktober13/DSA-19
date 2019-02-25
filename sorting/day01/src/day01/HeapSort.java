package day01;

public class HeapSort extends day01.SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {
        while (true) {
            if ((leftChild(i) < size) && (rightChild(i) < size)) { // If left and right child exist
                if ((heap[i] < heap[leftChild(i)]) || (heap[i] < heap[rightChild(i)])) { // One is larger than i
                    if (heap[leftChild(i)] > heap[rightChild(i)]) { // Replace with leftchild
                        i = replaceInHeap(i, leftChild(i));
                    } else {                                        // Replace with rightchild
                        i = replaceInHeap(i, rightChild(i));
                    }
                } else { // No reordering necessary
                    break;
                }
            } else if ((leftChild(i) < size) && (heap[leftChild(i)] > heap[i])) { // Only leftchild exists & is larger than i
                i = replaceInHeap(i, leftChild(i));                     // Replace with leftchild
            } else if ((rightChild(i) < size) && (heap[rightChild(i)] > heap[i])) { // Only rightchild exists & is larger than i
                i = replaceInHeap(i, rightChild(i));                    // Replace with rightchild
            } else {
                break;
            }
        }
    }

    public int replaceInHeap(int origIndex, int newIndex) {
        int temp = heap[origIndex];
        heap[origIndex] = heap[newIndex];
        heap[newIndex] = temp;
        return newIndex;
    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;

        for (int i=this.size / 2 - 1; i>=0; i--) {
            sink(i);
        }
    }

    /**
     * Best-case runtime: O(NlogN)
     * Worst-case runtime: O(NlogN)
     * Average-case runtime: O(NlogN)
     *
     * Space-complexity: O(N)
     */
    @Override
    public int[] sort(int[] array) {
        if (array.length <= 1) {
            return array;
        } else if (array.length == 2) {
            if (array[0] > array[1]) {
                int temp = array[1];
                array[1] = array[0];
                array[0] = temp;
            }
            return array;
        }
        heapify(array);

        size = array.length;

        for (int i=size-1; i>0; i--) {
            swap(array, i, 0);
            size--;
            sink(0);
        }

        return array;
    }
}
