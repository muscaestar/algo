package heap;

import java.util.Arrays;

/**
 * This is a fix size, min-heap.
 *
 * Created by muscaestar on 2/17/20
 *
 * @author muscaestar
 */
public class BinaryMinHeap<E extends Comparable<? super E>> {
    private E[] array; // store element
    private int size; // number of element stored
    private int capacity; // length of array - 1

    public BinaryMinHeap(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("!!! capacity must be positive");
        }
        this.capacity = capacity;
        array = (E[]) new Comparable[this.capacity + 1];
        size = 0;
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

    public E add(E e) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("!!! Heap is full");
        }

        int i = size + 1; // index of tail node, will be filled by e
        if (size++ == 0) { // check if empty, then add size
            array[i] = e;
        }
        i = siftUp(i, e);
        return array[i]; // the e just added
    }

    /**
     * Up-heapify the heap.
     * Right at the start of this method, the 'heap' is broken.
     * It is the tail node of the heap needed to be re-allocated.
     *
     * @param i the index of tail node
     * @param e the element going to be fill in
     * @return the index of the entry finally filled by e
     */
    private int siftUp(int i, E e) {
        while (i > 1) { // while the entry to fill is not root
            int p = i / 2; // current index of parent of entry to fill

            int cmp = e.compareTo(array[p]);
            if (cmp < 0) {
                array[i] = array[p];
                i = p;
            } else { // cmp >= 0
                break;
            }
        }
        array[i] = e; // fill the entry with e
        return i;
    }

    public E peak() {
        return (size == 0) ? null : array[1];
    }

}
