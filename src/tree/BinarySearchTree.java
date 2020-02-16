package tree;

/**
 * Created by muscaestar on 2/15/20
 *
 * @author muscaestar
 */
public class BinarySearchTree<E extends Comparable<? super E>> extends BinaryTree<E> {

    public BinarySearchTree(Node<E> root) {
        super(root);
    }

    public BinarySearchTree(E rootVal) {
        super(new Node<E>(rootVal));
    }

    public void printSortedEle() {
        inOrder(super.root);
    }

    public Node<E> insert(E x) {
        return insert(x, super.root);
    }

    public Node<E> insert(E x, Node<E> root) {

        if (x == null) {
            throw new IllegalArgumentException();
        }

        if (root == null) {
            return new Node<>(x);
        }
        int cmp = x.compareTo(root.element);
        if (cmp < 0) {
            root.leftNode = insert(x, root.leftNode);
        } else if (cmp > 0) {
            root.rightNode = insert(x, root.rightNode);
        } else { // cmp == 0
            // if insert value already exists, do nothing
        }
        return root;
    }

    public Node<E> find(E x) {
        return find(x, super.root);
    }

    public Node<E> find(E x, Node<E> root) {

        if (x == null) {
            throw new IllegalArgumentException();
        }

        if (root == null) {
            return null;
        }
        int cmp = x.compareTo(root.element);
        if (cmp < 0) {
            return find(x, root.leftNode);
        } else if (cmp > 0) {
            return find(x, root.rightNode);
        } else { // cmp == 0
            return root;
        }
    }

    public boolean contains(E x) {
        return contains(x, super.root);
    }

    public boolean contains(E x, Node<E> root) {

        if (x == null) {
            throw new IllegalArgumentException();
        }

        if (root == null) {
            return false;
        }
        int cmp = x.compareTo(root.element);
        if (cmp < 0) {
            return contains(x, root.leftNode);
        } else if (cmp > 0) {
            return contains(x, root.rightNode);
        } else { // cmp == 0
            return true;
        }
    }

    public Node<E> findMin() {
        return findMin(this.root);
    }

    public Node<E> findMin(Node<E> root) {
        if (root == null) {
            return null;
        }
        if (root.leftNode == null) {
            return root;
        }
        return findMin(root.leftNode);
    }

    public Node<E> findMax() {
        return findMax(this.root);
    }

    public Node<E> findMax(Node<E> root) {
        if (root == null) {
            return null;
        }
        if (root.rightNode == null) {
            return root;
        }
        return root.rightNode;
    }

    public Node<E> delete(E x) {
        super.root = delete(x, super.root);
        return root;
    }

    public Node<E> delete(E x, Node<E> root) {

        if (x == null) {
            throw new IllegalArgumentException();
        }

        if (root == null) {
            return null;
        }
        int cmp = x.compareTo(root.element);
        if (cmp < 0) {
            root.leftNode = delete(x, root.leftNode);
        } else if (cmp > 0) {
            root.rightNode = delete(x, root.rightNode);
        } else { // cmp == 0, so going to delete root

            if (root.leftNode != null && root.rightNode != null) { // two children
                // pick root's successor, replace root's element with successor's, then delete successor
                // otherwise, picking root's predecessor also work
                E newEleOfRoot = findMin(root.rightNode).element;
                root.element = newEleOfRoot;
                root.rightNode = delete(newEleOfRoot, root.rightNode);

            } else if (root.leftNode != null || root.rightNode != null) { // one child, just use it to replace root
                root = (root.leftNode != null) ? root.leftNode : root.rightNode;

            } else { // no child, just delete root
                root = null;
            }
        }
        return root;
    }

}
