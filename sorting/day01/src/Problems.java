package day01;

import java.util.*;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        ArrayList<Integer> seen = new ArrayList<Integer>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        return out;
    }


    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        double[] runningMedian = new double[inputStream.length];
        PriorityQueue<Integer> lesser = maxPQ();
        PriorityQueue<Integer> greater = minPQ();

        // Setup base cases
        if (inputStream.length == 1) {
            runningMedian[0] = inputStream[0];
        } else if (inputStream.length == 2) {
            runningMedian[0] = inputStream[0];
            runningMedian[1] = (inputStream[0] + inputStream[1]) / 2.0;
        } else if (inputStream.length >= 3) {
            runningMedian[0] = inputStream[0];
            runningMedian[1] = (inputStream[0] + inputStream[1]) / 2.0;
            int[] arr = Arrays.copyOfRange(inputStream, 0, 3);

            Arrays.sort(arr);
            runningMedian[2] = arr[1];
            lesser.add(arr[0]);
            greater.add(arr[1]);
            greater.add(arr[2]);

            for (int i = 3; i < inputStream.length; i++) {
                // Add to min/max PQs
                if (inputStream[i] < runningMedian[i-1]) {
                    lesser.add(inputStream[i]);
                } else if (inputStream[i] >= runningMedian[i-1]) {
                    greater.add(inputStream[i]);
                }

                // Resizing priority queues
                if (java.lang.Math.abs(lesser.size() - greater.size()) > 1) {
                    if (lesser.size() > greater.size()) {
                        greater.add(lesser.poll());
                    } else if (greater.size() > lesser.size()) {
                        lesser.add(greater.poll());
                    }
                }

                // Set running median value for i
                if (((i+1) % 2) == 0) { // even
                    runningMedian[i] = (((Integer) greater.peek()).doubleValue() + ((Integer) lesser.peek()).doubleValue()) / 2.0;
                } else { // odd
                    if (lesser.size() > greater.size()) {
                        runningMedian[i] = ((Integer) lesser.peek()).doubleValue();
                    } else if (greater.size() > lesser.size()) {
                        runningMedian[i] = ((Integer) greater.peek()).doubleValue();
                    } else if (greater.size() == lesser.size()) {
                        runningMedian[i] = runningMedian[i-2];
                    }
                }
            }
        }

        return runningMedian;
    }

}