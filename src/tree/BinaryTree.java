package tree;

public class BinaryTree<T> {
    private Node root;

    static class Node {
        Object self;
        Node leftNode;
        Node rightNode;

        public Node(Object self) {
            this.self = self;
        }

        public Node(Object self, Node leftNode, Node rightNode) {
            this.self = self;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        @Override
        public String toString() {
            return self.toString();
        }
    }

    public BinaryTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void preOrder(Node N) {
        if (N == null) {
            return;
        }
        System.out.print(N);
        preOrder(N.leftNode);
        preOrder(N.rightNode);
    }

    public void inOrder(Node N) {
        if (N == null) {
            return;
        }
        inOrder(N.leftNode);
        System.out.print(N);
        inOrder(N.rightNode);
    }

    public void postNode(Node N) {
        if (N == null) {
            return;
        }
        postNode(N.leftNode);
        postNode(N.rightNode);
        System.out.print(N);
    }
}
