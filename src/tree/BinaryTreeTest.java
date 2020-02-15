package tree;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class BinaryTreeTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private BinaryTree<Integer> threeLvBT;

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));

        /**
         *       1
         *     /   \
         *    2     3
         *   / \   / \
         *  4  5  6   7
         *
         */
        BinaryTree.Node leftInnerNode = new BinaryTree.Node(2, new BinaryTree.Node(4), new BinaryTree.Node(5));
        BinaryTree.Node rightInnerNode = new BinaryTree.Node(3, new BinaryTree.Node(6), new BinaryTree.Node(7));
        threeLvBT = new BinaryTree<>(
                new BinaryTree.Node(1, leftInnerNode, rightInnerNode));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void preOrder() {
        threeLvBT.preOrder(threeLvBT.getRoot());
        assertEquals("1245367", outContent.toString());
    }

    @Test
    public void inOrder() {
        threeLvBT.inOrder(threeLvBT.getRoot());
        assertEquals("4251637", outContent.toString());
    }

    @Test
    public void postNode() {
        threeLvBT.postNode(threeLvBT.getRoot());
        assertEquals("4526731", outContent.toString());
    }
}