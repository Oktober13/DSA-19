import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Collections.sort(values);

        childIndex(bst,values,0,values.size());

        return bst;
    }

    private static void childIndex (BinarySearchTree tree, List<Integer> values, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int index = (int) Math.floor((hi + lo) / 2.0);
        tree.add(values.get(index));
        childIndex(tree,values,lo, index);
        childIndex(tree,values,index+1,hi);
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        // TODO
        return false;
    }
}
