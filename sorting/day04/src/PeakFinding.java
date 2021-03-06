import java.util.Arrays;

public class PeakFinding {

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;
        return 0;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int y, int x, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x-1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x+1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int y, int x, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y-1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y+1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }


    public static int findOneDPeak(int[] nums) {
        int peak = -1;

        if (nums.length == 0) {
            return -1;
        } if (nums.length == 1) {
            return 0;
        } else if (nums.length == 2) {
            if (nums[0] > nums [1]) {
                return 0;
            } else if (nums[0] < nums [1]) {
                return 1;
            }
        } else {
            int leftbound = 0;
            int rightbound = nums.length - 1;
            int middle = (int) Math.floor((rightbound-leftbound) / 2.0);

            while ((rightbound-leftbound) > 2) {
                if (nums[middle] > nums[middle - 1]) {
                    leftbound = middle;
                } else if (nums[middle] < nums[middle - 1]) {
                    rightbound = middle;
                } else { // Peak
                    return middle;
                }
                middle = (int) Math.floor((rightbound - leftbound) / 2.0) + leftbound;
            }
            if (nums[leftbound] > nums [rightbound]) {
                return leftbound;
            } else if (nums[leftbound] < nums [rightbound]) {
                return rightbound;
            } else {
                return leftbound;
            }
        }
        return peak;
    }

    public static int[] findTwoDPeak(int[][] nums) {
        int[] coords = new int[2];
        int xStart = 0;
        int xEnd = nums.length - 1;
        int yStart = 0;
        int yEnd = nums[0].length - 1;

        coords[0] = (int) Math.floor(nums.length / 2.0); // Y
        coords[1] = (int) Math.floor(nums[0].length / 2.0); // X

        while (true) {
            // CONDITIONS

            if ((peakX(coords[0], coords[1], nums) == 0) && (peakY(coords[0], coords[1], nums) == 0)) {
                break;
            }

            if ((xEnd - xStart) > (yEnd - yStart)) {    // Partition vertically
                coords[0] = maxYIndex(coords[1], xStart, xEnd + 1, nums);
                if (coords[1] < 0) {
                    xEnd = (int) Math.floor((xEnd - xStart) / 2.0);
                } else {
                    xStart = (int) Math.floor((xEnd - xStart) / 2.0);
                }
            } else {    // Partition horizontally
                coords[1] = maxXIndex(coords[0], yStart, yEnd + 1, nums);
                if (coords[0] < (int) Math.floor((yEnd - yStart) / 2.0)) {
                    yEnd = (int) Math.floor((yEnd - yStart) / 2.0);
                } else {
                    yStart = (int) Math.floor((yEnd - yStart) / 2.0);
                }
            }
        }
        return coords;
    }

}
