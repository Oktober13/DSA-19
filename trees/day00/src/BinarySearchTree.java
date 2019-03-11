import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {
    TreeNode<T> root;
    private int size;

    public int size() {
        return size;
    }

    public boolean contains(T key) {
        return find(root, key) != null;
    }

    /**
     * Add a node to the BST. Internally calls insert to recursively find the new node's place
     */
    public boolean add(T key) {
        if (find(root, key) != null) return false;
        root = insert(root, key);
        size++;
        return true;
    }

    public void addAll(T[] keys) {
        for (T k : keys)
            add(k);
    }

    /** Performs an in-order traversal of the tree. **/
    public List<T> inOrderTraversal() {
        TreeNode<T> currNode = root;
        TreeNode<T> next;
        TreeNode<T> last;
        List<T> returnValues = new ArrayList<>();

        if (currNode == null) {
            return returnValues;
        }

        while (currNode.hasRightChild()) { // get final value
            currNode = currNode.rightChild;
        }
        last = currNode;
        currNode = root;

        while (currNode.hasLeftChild()) { // get initial node
            currNode = currNode.leftChild;
        }

        returnValues.add(currNode.key);

        while (true) {
            next = findSuccessor(currNode);
            if ((next == null) || (currNode.equals(last))) {
                break;
            }
            currNode = next;
            returnValues.add(currNode.key);
        }

        return returnValues;
    }

    /**
     * Deletes a node from the BST using the following logic:
     * 1. If the node has a left child, replace it with its predecessor
     * 2. Else if it has a right child, replace it with its successor
     * 3. If it has no children, simply its parent's pointer to it
     */
    public boolean delete(T key) {
        TreeNode<T> toDelete = find(root, key);
        if (toDelete == null) {
            System.out.println("Key does not exist");
            return false;
        }
        TreeNode<T> deleted = delete(toDelete);
        if (toDelete == root) {
            root = deleted;
        }
        size--;
        return true;
    }

    private TreeNode<T> delete(TreeNode<T> n) {
        // Recursive base case
        if (n == null) return null;

        TreeNode<T> replacement;
        TreeNode<T> predecessor;

        if (n.isLeaf())
            // Case 1: no children
            replacement = null;
        else if (n.hasRightChild() != n.hasLeftChild())
            // Case 2: one child
            replacement = (n.hasRightChild()) ? n.rightChild : n.leftChild; // replacement is the non-null child
        else {
            // Case 3: two children
            replacement = null;
            if (n.hasLeftChild()) {
                replacement = findPredecessor(n);
            } else if (n.hasRightChild()) {
                replacement = findSuccessor(n);
            }
            delete(replacement);
            replacement.moveChildrenFrom(n);
            // Childless node
        }

        // Put the replacement in its correct place, and set the parent.
        n.replaceWith(replacement);
        return replacement;
    }

    public T findPredecessor(T key) {
        // finds and returns the TreeNode with key = key if such a TreeNode exists in the tree
        TreeNode<T> n = find(root, key);
        if (n != null) {
            // get the predecessor TreeNode by calling the function you will implement below
            TreeNode<T> predecessor = findPredecessor(n);
            // return the key of predecessor TreeNode
            if (predecessor != null)
                return predecessor.key;
        }
        return null;
    }

    public T findSuccessor(T key) {
        // finds and returns the TreeNode with key = key if such a TreeNode exists in the tree
        TreeNode<T> n = find(root, key);
        if (n != null) {
            // get the successor TreeNode by calling the function you will implement below
            TreeNode<T> successor = findSuccessor(n);
            // return the key of successor TreeNode
            if (successor != null)
                return successor.key;
        }
        return null;
    }

    private TreeNode<T> findPredecessor(TreeNode<T> n) {
        TreeNode<T> currNode = null;
        if (n.hasLeftChild()) { // If smaller children exist, choose next smallest.
            currNode = n.leftChild;
            if (currNode.hasRightChild()) { // Better number available
                while (currNode.hasRightChild()) { // While larger numbers than currNode's value exist, return those
                    currNode = currNode.rightChild;
                }
            }
            // Return leftchild value- no numbers larger than leftchild yet smaller than n exist.
        } else if (n.parent != null) {
            if (n.isRightChild()) { // Is right child
                currNode = n.parent;
            } else { // Is left child
                currNode = n.parent;

                while ((currNode.parent != null) && (currNode.isLeftChild())) { // Move up BST
                    currNode = currNode.parent;
                }
                if (currNode.isRoot()) { // Was always left child
                    return null;
                }
                currNode = currNode.parent;
            }
        }
        return currNode;
    }

    private TreeNode<T> findSuccessor(TreeNode<T> n) {
        TreeNode<T> currNode = null;

        if (n.hasRightChild()) {
            currNode = n.rightChild;
            if (currNode.hasLeftChild()) { // Better number available
                while (currNode.hasLeftChild()) { // While smaller numbers than currNode's value exist, return those
                    currNode = currNode.leftChild;
                }
            }
            // Return rightchild value- no numbers larger than currNode yet smaller than n exist.
        } else if (n.parent != null) {
            if (n.isLeftChild()) { // Is left child
                currNode = n.parent;
            } else { // Is right child
                currNode = n.parent;

                while ((currNode.parent != null) && (currNode.isRightChild())) { // Move up BST
                    currNode = currNode.parent;
                }
                if (currNode.isRoot()) { // Was always right child
                    return null;
                }
                currNode = currNode.parent;
            }
        }
        return currNode;
    }

    /**
     * Returns a node with the given key in the BST, or null if it doesn't exist.
     */
    private TreeNode<T> find(TreeNode<T> currentNode, T key) {
        if (currentNode == null)
            return null;
        int cmp = key.compareTo(currentNode.key);
        if (cmp < 0)
            return find(currentNode.leftChild, key);
        else if (cmp > 0)
            return find(currentNode.rightChild, key);
        return currentNode;
    }

    /**
     * Recursively insert a new node into the BST
     */
    public TreeNode<T> insert(TreeNode<T> node, T key) {
        if (node == null) return new TreeNode<>(key);

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.leftChild = insert(node.leftChild, key);
            node.leftChild.parent = node;
        } else {
            node.rightChild = insert(node.rightChild, key);
            node.rightChild.parent = node;
        }
        return node;
    }
}
