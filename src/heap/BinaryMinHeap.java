package heap;

import java.util.Arrays;
import java.util.Objects;

/**
 * This is a fix size, min-heap.
 *
 * Created by muscaestar on 2/17/20
 *
 * @author muscaestar
 */
public class BinaryMinHeap<E extends Comparable<? super E>> {
    private E[] array; // store elements
    private int size; // number of elements stored
    private int capacity; // length of array - 1

    public BinaryMinHeap(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("!!! capacity must be positive");
        }
        this.capacity = capacity;
        array = (E[]) new Comparable[this.capacity + 1];
        size = 0;
    }

    public BinaryMinHeap(int capacity, E[] rawArray) {
        if (rawArray.length > capacity) {
            throw new IllegalArgumentException("!!! capacity should at least equal to the length of input array");
        }
        if (Arrays.stream(rawArray).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("!!! input array should not contain null");
        }

        this.capacity = capacity;
        array = (E[]) new Comparable[this.capacity + 1];
        System.arraycopy(rawArray,0, array,1,rawArray.length);
        size = rawArray.length;

        build();
    }

    public E[] getArray() {
        return Arrays.copyOf(array, capacity + 1);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isFull() {
        return size >= capacity + 1;
    }

    public E peak() {
        return (size == 0) ? null : array[1];
    }

    public E add(E e) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("!!! Heap is full");
        }

        // size plus one, and make tail be the empty node
        int i = size + 1; // index of empty node
        if (size++ == 0) { // check if heap is empty, then add size
            array[i] = e;
        }
        i = siftUp(i, e);
        return array[i]; // the e just added
    }

    /**
     * Heapify-up the heap.
     * At the start of this method, the 'heap' has an empty node.
     * This method re-allocate the empty node by keeping comparing it
     * with its parent, then swap if need.
     * After re-allocation, fill element e into it.
     *
     * @param i the index of empty node
     * @param e the element going to fill in
     * @return the final index of original empty node
     */
    private int siftUp(int i, E e) {
        while (i > 1) { // while the empty node is not the top
            int parent = i / 2;

            int cmp = e.compareTo(array[parent]);
            if (cmp < 0) {
                array[i] = array[parent];
                i = parent;
            } else { // cmp >= 0
                break;
            }
        }
        array[i] = e; // fill the empty node with e
        return i;
    }

    public E poll() {
        E res = array[1];
        array[1] = null; // make top be the empty node

        E e = array[size]; // the value of tail node will be fill in later
        array[size--] = null; // remove the tail node, size - 1

        siftDown(1, e);
        return res;
    }

    /**
     * Heapify-down the heap.
     * At the start of this method, the 'heap' has an empty node.
     * This method re-allocate the empty node by keeping comparing it
     * with its least child, then swap if need.
     * After re-allocation, fill element e into it.
     *
     * @param i the index of the empty node
     * @param e the element going to fill in
     * @return the final index of the original empty node
     */
    private int siftDown(int i, E e) {
        while (i <= size / 2) { // make sure empty node is non-leaf
            int ch1 = i * 2; // index of child 1
            int ch2 = ch1 + 1; // index of child 2, if exist

            int leastChild = ch1;
            // if ch2 exist, then compare children to get the least one
            if (ch2 <= size && array[ch1].compareTo(array[ch2]) > 0) {
                leastChild = ch2;
            }

            int cmp = e.compareTo(array[leastChild]);
            if (cmp > 0) {
                E tmp = array[leastChild];
                array[leastChild] = array[i];
                array[i] = tmp;
                i = leastChild;
            } else {
                break;
            }
        }
        array[i] = e; // fill the empty node with e
        return i;
    }

    /**
     * Build a heap from an unsroted array.
     *
     * @return true
     */
    private boolean build() {
        for (int i = size / 2; i >= 1; i--) {
            siftDown(i, array[i]);
        }
        return true;
    }
}
