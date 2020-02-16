package tree;

/**
 * Created by muscaestar on 2/15/20
 *
 * @author muscaestar
 */
public class BinaryTree<E> {
    protected Node<E> root;

    static class Node<E> {
        E element;
        Node<E> leftNode;
        Node<E> rightNode;

        public Node(E element) {
            this.element = element;
        }

        public Node(E element, Node<E> leftNode, Node<E> rightNode) {
            this.element = element;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    public BinaryTree(Node<E> root) {
        this.root = root;
    }

    public Node<E> getRoot() {
        return root;
    }

    public void setRoot(Node<E> root) {
        this.root = root;
    }

    public void preOrder(Node<E> N) {
        if (N == null) {
            return;
        }
        System.out.print(N);
        preOrder(N.leftNode);
        preOrder(N.rightNode);
    }

    public void inOrder(Node<E> N) {
        if (N == null) {
            return;
        }
        inOrder(N.leftNode);
        System.out.print(N);
        inOrder(N.rightNode);
    }

    public void postNode(Node<E> N) {
        if (N == null) {
            return;
        }
        postNode(N.leftNode);
        postNode(N.rightNode);
        System.out.print(N);
    }
}
