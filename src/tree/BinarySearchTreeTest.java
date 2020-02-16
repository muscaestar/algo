package tree;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tree.BinaryTree.Node;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by muscaestar on 2/15/20
 *
 * @author muscaestar
 */
public class BinarySearchTreeTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private BinarySearchTree<Integer> twoLvBST;

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));

        /*
         *       5
         *     /   \
         *    1     8
         */
//        twoLvBST = new BinarySearchTree<>(new Node<>(5, new Node<>(1), new Node<>(8)));
        twoLvBST = new BinarySearchTree<>(5);
        twoLvBST.insert(8);
        twoLvBST.insert(1);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(originalOut);
    }

    @Test
    public void printSortedEle() {
        twoLvBST.printSortedEle();
        assertEquals("158", outContent.toString());
    }

    @Test
    public void insert() {
        Node<Integer> returnedRoot3 = twoLvBST.insert(3, twoLvBST.getRoot());
        twoLvBST.insert(0, twoLvBST.getRoot());
        twoLvBST.insert(6, twoLvBST.getRoot());
        Node<Integer> returnedRoot7 = twoLvBST.insert(7);
        twoLvBST.insert(9, twoLvBST.getRoot());
        Node<Integer> returnedRoot5 = twoLvBST.insert(5); //should be ignored

        twoLvBST.inOrder(twoLvBST.getRoot());

        assertEquals("01356789", outContent.toString());

        assertEquals(Integer.valueOf(5), returnedRoot3.element);
        assertEquals(Integer.valueOf(5), returnedRoot7.element);
        assertEquals(Integer.valueOf(5), returnedRoot5.element);
    }

    @Test
    public void find() {
        Node<Integer> returnedNode1 = twoLvBST.find(1, twoLvBST.getRoot());
        Node<Integer> returnedNode5 = twoLvBST.find(5, twoLvBST.getRoot());
        Node<Integer> returnedNode8 = twoLvBST.find(8);
        Node<Integer> returnedNodeNull = twoLvBST.find(100); // should be null
        assertEquals(Integer.valueOf(1), returnedNode1.element);
        assertEquals(Integer.valueOf(5), returnedNode5.element);
        assertEquals(Integer.valueOf(8), returnedNode8.element);
        assertNull(returnedNodeNull);
    }

    @Test
    public void contains() {
        boolean result1 = twoLvBST.contains(1, twoLvBST.getRoot());
        boolean result5 = twoLvBST.contains(5, twoLvBST.getRoot());
        boolean result8 = twoLvBST.contains(8);
        boolean resultNotExist = twoLvBST.contains(100); // should be false
        assertTrue(result1);
        assertTrue(result5);
        assertTrue(result8);
        assertFalse(resultNotExist);
    }

    @Test
    public void findMin() {
        Node<Integer> returnedValue = twoLvBST.findMin();
        assertEquals(Integer.valueOf(1), returnedValue.element);
    }

    @Test
    public void findMax() {
        Node<Integer> returnedValue = twoLvBST.findMax();
        assertEquals(Integer.valueOf(8), returnedValue.element);
    }

    @Test
    public void delete_5() {
        twoLvBST.delete(5);
        twoLvBST.printSortedEle();
        assertEquals("18", outContent.toString());
    }

    @Test
    public void delete_1() {
        Node<Integer> ret = twoLvBST.delete(1);
        twoLvBST.printSortedEle();
        assertEquals("58", outContent.toString());
        assertEquals(Integer.valueOf(5), ret.element); // root should be eventually returned
    }

    @Test
    public void delete_8() {
        twoLvBST.delete(8);
        twoLvBST.printSortedEle();
        assertEquals("15", outContent.toString());
    }

    /*
     *       5
     *     /   \
     *    1     8
     *         /
     *        6
     *         \
     *          7
     */
    @Test
    public void delete_4Level_5() {
        twoLvBST.insert(6);
        twoLvBST.insert(7);
        Node<Integer> ret = twoLvBST.delete(5);
        twoLvBST.printSortedEle();
        assertEquals("1678", outContent.toString());
        assertEquals(Integer.valueOf(6), twoLvBST.root.element); // new root should be 6
        assertEquals(Integer.valueOf(6), ret.element); // new root should be eventually returned
    }

    @Test
    public void delete_onlyRoot() {
        BinarySearchTree<String> onlyRootTree = new BinarySearchTree<>("only");
        onlyRootTree.delete("only");
        onlyRootTree.printSortedEle();
        assertNull(onlyRootTree.root);
    }

    @Test
    public void deleteNotExist() {
        Node<Integer> ret = twoLvBST.delete(7);
        twoLvBST.printSortedEle();
        assertEquals("158", outContent.toString());
        assertEquals(Integer.valueOf(5), ret.element);
    }
}
