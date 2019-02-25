import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class TripleSum {
    static int tripleSum(int arr[], int sum) {
        ArrayList< ArrayList <Integer> > pair = new ArrayList<>();
        ArrayList<Integer> triplets = new ArrayList<>();
        LinkedList<String> prevTriplets = new LinkedList<>();

        sort(arr);

        for (int i = 0; i < arr.length - 2; i++) {
            int firstElement = i + 1;
            int lastElement = arr.length -1;

            while (firstElement < lastElement) {
                if (arr[i] + arr[firstElement] + arr[lastElement] == sum) {

                    String str = arr[i] + ":" + arr[firstElement] + ":" + arr[lastElement];

                    if (!prevTriplets.contains(str)) {

                        // To check for the unique triplet
                        triplets.add(arr[i]);
                        triplets.add(arr[firstElement]);
                        triplets.add(arr[lastElement]);
                        pair.add(triplets);
                        triplets = new ArrayList<>();
                        prevTriplets.add(str);
                    }

                    firstElement++; // increment the second value index
                    lastElement--; // decrement the third value index

                } else if (arr[i] + arr[firstElement] + arr[lastElement] < sum) {
                    firstElement++;
                } else { // nums[i] + nums[j] + nums[k] > sum
                    lastElement--;
                }
            }
        }

        return pair.size();
    }

    private static void swap(int[] var1, int var2, int var3) {
        int var4 = var1[var2];
        var1[var2] = var1[var3];
        var1[var3] = var4;
    }

    private static void shuffleArray(int[] var1) {
        for(int var2 = 0; var2 < var1.length; ++var2) {
            int var3 = ThreadLocalRandom.current().nextInt(var2 + 1);
            swap(var1, var2, var3);
        }

    }

    public static int[] sort(int[] var1) {
        shuffleArray(var1);
        quickSort(var1, 0, var1.length - 1);
        return var1;
    }

    public static void quickSort(int[] var1, int var2, int var3) {
        if (var2 < var3) {
            int var4 = partition(var1, var2, var3);
            quickSort(var1, var2, var4 - 1);
            quickSort(var1, var4 + 1, var3);
        }

    }

    public static int partition(int[] var1, int var2, int var3) {
        int var4 = var1[var2];
        int var5 = var2;

        for (int var6 = var2; var6 <= var3; ++var6) {
            if (var1[var6] < var4) {
                ++var5;
                swap(var1, var5, var6);
            }
        }

        swap(var1, var2, var5);
        return var5;
    }
}

//private class QuickSort {
//    private static final int INSERTION_THRESHOLD = 10;
//
//    public QuickSort() {
//    }
//
//    void swap(int[] var1, int var2, int var3) {
//        int var4 = var1[var2];
//        var1[var2] = var1[var3];
//        var1[var3] = var4;
//    }
//
//    private void shuffleArray(int[] var1) {
//        for(int var2 = 0; var2 < var1.length; ++var2) {
//            int var3 = ThreadLocalRandom.current().nextInt(var2 + 1);
//            this.swap(var1, var2, var3);
//        }
//
//    }
//
//    public int[] sort(int[] var1) {
//        this.shuffleArray(var1);
//        this.quickSort(var1, 0, var1.length - 1);
//        return var1;
//    }
//
//    public void quickSort(int[] var1, int var2, int var3) {
//        if (var2 < var3) {
//            int var4 = this.partition(var1, var2, var3);
//            this.quickSort(var1, var2, var4 - 1);
//            this.quickSort(var1, var4 + 1, var3);
//        }
//
//    }
//
//    public int partition(int[] var1, int var2, int var3) {
//        int var4 = var1[var2];
//        int var5 = var2;
//
//        for(int var6 = var2; var6 <= var3; ++var6) {
//            if (var1[var6] < var4) {
//                ++var5;
//                this.swap(var1, var5, var6);
//            }
//        }
//
//        this.swap(var1, var2, var5);
//        return var5;
//    }
//}

//    static int tripleSum(int arr[], int sum) {
//        HashMap<Integer, Integer> validSolutions = new HashMap<>();
//        int maxDifference = 0;
//
//        for (int i = 1; i < arr.length; i++) {
//            if (Math.abs( arr[i] - arr[i-1] ) > maxDifference) {
//                maxDifference = Math.abs( arr[i] - arr[i-1]);
//            }
//        }
//
//        if (maxDifference == 0) { // All numbers in arr are the same.
//            if (sum == arr[0]) {
//                if (sum == 0) { // Can have as many as you want
//
//                } else { // Cannot fulfill sum condition.
//                    return -1;
//                }
//            } else {
//                return -1; // Cannot fulfill sum condition.
//            }
//        } else if (maxDifference == 1) { // Inclusive continuous bounds exist
//            int lowIndex = (arr[0] > arr[arr.length - 1]) ? 0 : (arr.length - 1); // Orientation
//            int highIndex = (arr[0] > arr[arr.length - 1]) ? (arr.length - 1) : 0;
//            return ramanujan(sum + (3 - 1)) / ramanujan(sum) / ramanujan(3 - 1);
//        }
//        return 0;
//    }
//
//    static int ramanujan(int x) {
//        /* Approximation function for x! using Ramanujan's factorial approximation method. */
//        int returnValue;
//
//        double factorial = Math.sqrt(Math.PI) * Math.pow(x / Math.exp(1), 2);
//        factorial *= Math.pow((((8*x + 4)*x + 1)*x + 1/30.), (1./6.));
//
//        return (int) factorial;
//    }
//}
