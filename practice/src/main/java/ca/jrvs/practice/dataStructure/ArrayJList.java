package ca.jrvs.practice.dataStructure;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

public class ArrayJList<E> implements JList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    transient Object[] elementData;

    private int size;

    public ArrayJList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity " + initialCapacity);
        }
    }

    public ArrayJList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    private void ensureInternalCapacity(int minCapacity) {
        this.ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        if (minCapacity > this.elementData.length) {
            this.grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = this.elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if ( newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        this.elementData = Arrays.copyOf(this.elementData, newCapacity);
    }

    private boolean checkForIndex(int i) {
        if (i < 0 || i > this.elementData.length) {
            return false;
        }
        return true;
    }

    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * @param e element to be appended to this list
     * @return <tt>true</tt> (as specified by {@link Collection#add})
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     */
    @Override
    public boolean add(E e) {
        this.ensureInternalCapacity(this.size + 1);
        this.elementData[this.size++] = e;
        return true;
    }

    /**
     * Returns an array containing all the elements in this list in proper
     * sequence (from first to last element).
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all the elements in this list in proper
     * sequence
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.elementData, this.size);
    }

    /**
     * The size of the ArrayList (the number of elements it contains).
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns <tt>true</tt> if this list contains no elements.
     *
     * @return <tt>true</tt> if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return this.elementData.length == 0;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @param o
     */
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (this.elementData[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns <tt>true</tt> if this list contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this list contains
     * at least one element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * @param o element whose presence in this list is to be tested
     * @return <tt>true</tt> if this list contains the specified element
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     */
    @Override
    public boolean contains(Object o) {
        for (Object x : this.elementData) {
            if (x.equals(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param i index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    public E get(int i) {
        if (this.checkForIndex(i)) {
            return (E) this.elementData[i];
        }
        throw new IndexOutOfBoundsException("Index non valid: " + i);
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * @param i the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public E remove(int i) {
        return null;
    }

    /**
     * Removes all the elements from this list (optional operation).
     * The list will be empty after this call returns.
     */
    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.elementData[i] = null;
        }
    }
}
