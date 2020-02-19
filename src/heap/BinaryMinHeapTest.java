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

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));

        bHeap = new BinaryMinHeap<>(HEAP_CAPACITY);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(originalOut);
    }

    @Test
    public void add() {
        bHeap.add("D");
        assertEquals("D", bHeap.peak());

        bHeap.add("B");
        assertEquals("B", bHeap.peak());

        bHeap.add("C");
        assertEquals("B", bHeap.peak());

        bHeap.add("A");
        assertEquals("A", bHeap.peak());

        bHeap.add("D");
        assertEquals(5, bHeap.getSize());
        assertEquals("A", bHeap.peak());
        System.out.println(Arrays.toString(bHeap.getArray()));
        assertEquals("[null, A, B, C, D, D]\n", outContent.toString());
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
}