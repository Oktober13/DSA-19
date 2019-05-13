public class FirstFailingVersion {

    //Runtime: N log(N)
    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        // Binary search

        long r = n;
        long l = 1;

        // While left and right window indices do not overlap
        while (l < r) {
            // Find middle index
            long mid = l + (r - l) / 2;

            // Divide in half
            if (isBadVersion.isFailingVersion(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        // Return overlapped index
        return l;
    }
}
