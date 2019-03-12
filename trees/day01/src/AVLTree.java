public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    TreeNode<T> delete(TreeNode<T> n, T key) {
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
    TreeNode<T> insert(TreeNode<T> n, T key) {
        n = super.insert(n, key);
        if (n != null) {
            n = balance(n);
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return n;
        }
        return null;
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    TreeNode<T> deleteMin(TreeNode<T> n) {
        n = super.deleteMin(n);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    // Return the height of the given node. Return -1 if null.
    private int height(TreeNode<T> n) {
        if (n == null) {
            return -1;
        }

        return n.height;
    }

    public int height() {
        return Math.max(height(root), 0);
    }

    // Restores the AVL tree property of the subtree. Return the head of the new subtree
    TreeNode<T> balance(TreeNode<T> n) {
        int balanced = balanceFactor(n);

        if (balanced > 1) { // Right heavy
            if (balanceFactor(n.rightChild) < 0) { // Child is left heavy
                n.rightChild = rotateRight(n.rightChild);
            }
            return rotateLeft(n);
        } else if (balanced < -1) { // Left heavy
            if (balanceFactor(n.leftChild) > 0) { // Child is right heavy
                n.leftChild = rotateLeft(n.leftChild);
            }
            return rotateRight(n);
        } else {
            return n;
        }
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     */
    private int balanceFactor(TreeNode<T> n) {
        return height(n.rightChild) - height(n.leftChild);
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateRight(TreeNode<T> n) {
        TreeNode<T> m = n.leftChild;
        TreeNode<T> m_rightchild = m.rightChild;
        m.rightChild = n;
        n.leftChild = m_rightchild;
        n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
        m.height = 1 + Math.max(height(m.leftChild), height(m.rightChild));
        return m;
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateLeft(TreeNode<T> n) {
        TreeNode<T> m = n.rightChild;
        TreeNode<T> m_leftchild = m.leftChild;
        m.leftChild = n;
        n.rightChild = m_leftchild;
        n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
        m.height = 1 + Math.max(height(m.leftChild), height(m.rightChild));
        return m;
    }
}
