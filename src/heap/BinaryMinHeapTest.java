package heap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by muscaestar on 2/18/20
 *
 * @author muscaestar
 */
public class BinaryMinHeapTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private final BinaryMinHeap<String> emptyHeap = new BinaryMinHeap<>(1);
    private BinaryMinHeap<String> bHeap;
    public static final int HEAP_CAPACITY = 5;
    private String[] inputUnsortedArr;

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));

        bHeap = new BinaryMinHeap<>(HEAP_CAPACITY);
        inputUnsortedArr = new String[]{"F", "E", "D", "C", "B", "A"};
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(originalOut);
    }

    @Test
    public void add() {
        bHeap.add("D");
        assertEquals("D", bHeap.peak());
        assertEquals("[null, D, null, null, null, null]", Arrays.toString(bHeap.getArray()));

        bHeap.add("B");
        assertEquals("B", bHeap.peak());
        assertEquals("[null, B, D, null, null, null]", Arrays.toString(bHeap.getArray()));

        bHeap.add("C");
        assertEquals("B", bHeap.peak());
        assertEquals("[null, B, D, C, null, null]", Arrays.toString(bHeap.getArray()));

        bHeap.add("A");
        assertEquals("A", bHeap.peak());
        assertEquals("[null, A, B, C, D, null]", Arrays.toString(bHeap.getArray()));

        bHeap.add("D");
        assertEquals(5, bHeap.getSize());
        assertEquals("A", bHeap.peak());
        assertEquals("[null, A, B, C, D, D]", Arrays.toString(bHeap.getArray()));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addToFull() {
        emptyHeap.add("A");
        emptyHeap.add("A");
    }

    @Test
    public void peakEmptyHeap() {
        assertNull(emptyHeap.peak());
    }

    @Test
    public void poll() {
        bHeap.add("A");
        bHeap.add("C");
        bHeap.add("B");
        bHeap.add("E");
        bHeap.add("D");
        assertEquals("[null, A, C, B, E, D]", Arrays.toString(bHeap.getArray()));

        assertEquals("A", bHeap.poll());
        assertEquals("[null, B, C, D, E, null]", Arrays.toString(bHeap.getArray()));

        assertEquals("B", bHeap.poll());
        assertEquals("[null, C, E, D, null, null]", Arrays.toString(bHeap.getArray()));

        assertEquals("C", bHeap.poll());
        assertEquals("[null, D, E, null, null, null]", Arrays.toString(bHeap.getArray()));

        assertEquals("D", bHeap.poll());
        assertEquals("[null, E, null, null, null, null]", Arrays.toString(bHeap.getArray()));

        assertEquals("E", bHeap.poll());
        assertEquals("[null, null, null, null, null, null]", Arrays.toString(bHeap.getArray()));
    }

    @Test
    public void pollEmptyHeap() {
        assertNull(emptyHeap.poll());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIllegalArgs1() {
        BinaryMinHeap<String> bmh = new BinaryMinHeap<>(5, inputUnsortedArr);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIllegalArgs2() {
        String[] input = new String[]{"A", "B", null, null};
        BinaryMinHeap<String> bmh = new BinaryMinHeap<>(10, input);
    }

    @Test
    public void testConstructorHappyPath() {
        BinaryMinHeap<String> bmh = new BinaryMinHeap<>(10, inputUnsortedArr);
        assertEquals("[null, A, B, D, C, E, F, null, null, null, null]", Arrays.toString(bmh.getArray()));
    }

    @Test
    public void test() {
        System.setOut(originalOut);
        int i = Integer.MAX_VALUE;
        System.out.println("value of i is " + i);
        System.out.println(Integer.toBinaryString(i));
//        i = i << 1;
        i = i * 4;
        System.out.println("after left shift, value of i is " + i);
        System.out.println(Integer.toBinaryString(i));
    }
}