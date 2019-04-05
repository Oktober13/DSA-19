package range_finding;

import java.util.*;

public class AVLRangeTree extends BinarySearchTree<Integer> {

    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    RangeNode<Integer> delete(RangeNode<Integer> n, Integer key) {
        n = super.delete(n, key);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    /**
     * Insert a key into the tree rooted at the given node.
     */
    @Override
    RangeNode<Integer> insert(RangeNode<Integer> n, Integer key) {
        n = super.insert(n, key);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    RangeNode<Integer> deleteMin(RangeNode<Integer> n) {
        n = super.deleteMin(n);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    // Return the height of the given node. Return -1 if null.
    private int height(RangeNode x) {
        if (x == null) return -1;
        return x.height;
    }

    public int height() {
        return Math.max(height(root), 0);
    }

    // Restores the AVL tree property of the subtree.
    RangeNode<Integer> balance(RangeNode<Integer> x) {
        if (balanceFactor(x) > 1) {
            if (balanceFactor(x.rightChild) < 0) {
                x.rightChild = rotateRight(x.rightChild);
            }
            x = rotateLeft(x);
        } else if (balanceFactor(x) < -1) {
            if (balanceFactor(x.leftChild) > 0) {
                x.leftChild = rotateLeft(x.leftChild);
            }
            x = rotateRight(x);
        }
        return x;
    }

    // Return all keys that are between [lo, hi] (inclusive).
    // TODO: runtime = O(?)
    public List<Integer> rangeIndex(int lo, int hi) {
        List<Integer> hikeys = new LinkedList<>();
        List<Integer> lokeys = new LinkedList<>();

        getKeys(hikeys, hi, root);
        getKeys(lokeys, lo, root);
        Set<Integer> ad = new HashSet<Integer>(hikeys);
        Set<Integer> bc = new HashSet<Integer>(lokeys);

        System.out.println("OwO");
        System.out.println(lo);
        System.out.println(hi);
        System.out.println(this.inOrderTraversal());
        System.out.println(hikeys.toString());
        System.out.println(lokeys.toString());

        bc.remove(lo);
        ad.removeAll(bc);

        System.out.println(ad.toString());
        System.out.println(bc.toString());

        ArrayList<Integer> result = new ArrayList<Integer>(ad);
        Collections.sort(result);
        return result;
    }

    public void getKeys(List<Integer> keys, int k, RangeNode node) {
        if (node == null) {
            return;
        }

        if (node.key.compareTo(k) <= 0) { // If num is larger than current node's key
            keys.add((Integer) node.key);
            getKeys(keys,k,node.leftChild);
            getKeys(keys,k,node.rightChild);
        } else { // If num is less than current nodes' key
            getKeys(keys, k, node.leftChild);
        }
    }

    // return the number of keys between [lo, hi], inclusive
    // TODO: runtime = O(?)
    public int rangeCount(int lo, int hi) {
        int high = rank(root, hi + 1);
        int low = rank(root, lo);
        return high - low;
    }

    public int rank(RangeNode node, int k) { // returns the number of keys <= k
        if (node == null) {
            return -1;
        }
        int lessthan;

        if (node.key.compareTo(k) < 0) { // If num is larger than current node's key
            int subtree = (node.leftChild != null) ? node.leftChild.subNodes : 0;
            lessthan = 1 + subtree + rank(node.rightChild, k);
        } else { // If num is less than current nodes' key
            lessthan = rank(node.leftChild, k);
        }
        return lessthan;
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     */
    private int balanceFactor(RangeNode x) {
        return height(x.rightChild) - height(x.leftChild);
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private RangeNode<Integer> rotateRight(RangeNode<Integer> x) {
        RangeNode<Integer> y = x.leftChild;
        x.leftChild = y.rightChild;
        y.rightChild = x;
        x.height = 1 + Math.max(height(x.leftChild), height(x.rightChild));
        y.height = 1 + Math.max(height(y.leftChild), height(y.rightChild));

        if (y.leftChild == null) {
            y.subNodes = y.rightChild.subNodes + 1;
        } else {
            y.subNodes = y.leftChild.subNodes + y.rightChild.subNodes + 1;
        }

        if ((x.leftChild == null) && (x.rightChild == null)){
            x.subNodes = 1;
        } else if (x.leftChild == null) {
            x.subNodes = x.rightChild.subNodes + 1;
        }
        else if (x.rightChild == null) {
            x.subNodes = x.leftChild.subNodes + 1;
        } else {
            x.subNodes = x.leftChild.subNodes + x.rightChild.subNodes + 1;
        }

        return y;
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private RangeNode<Integer> rotateLeft(RangeNode<Integer> x) {
        RangeNode<Integer> y = x.rightChild;
        x.rightChild = y.leftChild;
        y.leftChild = x;
        x.height = 1 + Math.max(height(x.leftChild), height(x.rightChild));
        y.height = 1 + Math.max(height(y.leftChild), height(y.rightChild));

        if (y.rightChild != null) {
            y.subNodes = y.leftChild.subNodes + y.rightChild.subNodes + 1;
        }

        if ((x.leftChild == null) && (x.rightChild == null)){
            x.subNodes = 1;
        } else if (x.leftChild == null) {
            x.subNodes = x.rightChild.subNodes + 1;
        }
        else if (x.rightChild == null) {
            x.subNodes = x.leftChild.subNodes + 1;
        } else {
            x.subNodes = x.leftChild.subNodes + x.rightChild.subNodes + 1;
        }

        return y;
    }
}
