import java.util.*;

public class Permutations {
    public static List<List<Integer>> permutations(List<Integer> A) {
        List<List<Integer>> returnVal = new LinkedList<>();
        int num = A.size();

        Permutations.permute(returnVal, A, 0, num-1);
        return returnVal;
    }

    private static void permute(List<List<Integer>> p, List<Integer> mat, int l, int r) {
        if (l == r) { // If lower range == higher range (number has been filled)
            p.add(new LinkedList<>(mat));
        } else {
            for (int i = l; i <= r; i++) { // Iterate through range
                mat = swap(mat,l,i); // Reorder number ABC -> BAC
                permute(p, mat, l+1, r); // Recurse, move window one number right.
                mat = swap(mat,l,i); // Reorder number ABC -> BAC
            }
        }
    }

    public static List<Integer> swap(List<Integer> A, int i, int j)
    {
        Integer temp;
        temp = A.get(i) ;
        A.set(i, A.get(j));
        A.set(j, temp);
        return A;
    }

}
