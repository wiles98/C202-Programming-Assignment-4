package dictionary;

/**
 * CSCI C202
 * Lab 6 - Linked Lists
 * Purpose: Build LinkedLists to conveniently store information in nodes and
 *          write various methods to manipulate the nodal data once stored
 * 
 * @author Thomas Wiles
 * @version 1.0 3/22/17
 * @param <E> element type E, a reference type element
 */

public class MyLinkedList<E> extends MyAbstractList<E> {
    
    private Node<E> head, tail;

    /** Create a default list */
    public MyLinkedList() {
    }

    /** Create a list from an array of objects */
    public MyLinkedList(E[] objects) {
        super(objects);
    }

    /** Return the head element in the list */
    public E getFirst() {
       if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    /** Return the last element in the list */
    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    /** Add an element to the beginning of the list */
    public void addFirst(E e) {
        Node<E> newNode = new Node<E>(e); // Create a new node
        newNode.next = head; // link the new node with the head
        head = newNode; // head points to the new node
        size++; // Increase list size
        if (tail == null) // the new node is the only node in list
        {
            tail = head;
        }
    }

    /** Add an element to the end of the list */
    public void addLast(E e) {
        Node<E> newNode = new Node<E>(e); // Create a new for element e
        if (tail == null) {
            head = tail = newNode; // The new node is the only node in list
        } else {
            tail.next = newNode; // Link the new with the last node
            tail = tail.next; // tail now points to the last node
        }
        size++; // Increase size
    }

    /** Add a new element at the specified index in this list
     * The index of the head element is 0 */
    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else if (index >= size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<E>(e);
            (current.next).next = temp;
            size++;
        }
    }

    /** Remove the head node and
     *  return the object that is contained in the removed node. */
    public E removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Node<E> temp = head;
            head = head.next;
            size--;
            if (head == null) {
                tail = null;
            }
            return temp.element;
        }
    }

    /** Remove the last node and
     * return the object that is contained in the removed node. */
    public E removeLast() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        } else {
            Node<E> current = head;
            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }
            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    /** Remove the element at the specified position in this list.
     *  Return the element that was removed from the list. */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> previous = head;
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }
            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }

    /** Override toString() to return elements in the list */
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            } else {
                result.append("]"); // Insert the closing ] in the string
            }
        }
        return result.toString();
    }

    /** Clear the list */
    public void clear() {
        head = tail = null;
    }
    
    /**
     * checks if the LinkedList contains an element
     * @param e - the element sought for
     * @return - boolean, true if element is found in list
     */
    public boolean contains(E e) {
        Node<E> temp = head;
        while (temp != null) {
            if (e.equals(temp.element))
                return true;
            temp = temp.next;
        } //while
        return false;
    } //contains
    
    /**
     * checks if the LinkedList contains an element & counts comparisons
     * @param e - the element sought for
     * @param count - long array of a single value
     * @return - boolean, true if element is found in list
     */
    public boolean contains(E e, long[] count) {
        count[0] = 0;
        Node<E> temp = head;
        while (temp != null) {
            count[0]++;
            if (e.equals(temp.element))
                return true;
            temp = temp.next;
        } //while
        return false;
    } //contains
    
    /**
     * finds the element at a certain position
     * @param index - integer value for the position of the element
     * @return - the element at the index (position)
     */
    public E get(int index) {
        if (index < 0 || index >= size)
            return null;
        else {
            Node<E> temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            } //for
            return temp.element;
        } //else
    } //get
    
    /**
     * resets the element at the index to a new value
     * @param index - integer for the position of the element
     * @param e - element to be placed in the list
     * @return - element that is being replaced
     */
    public E set(int index, E e) {
        if (index < 0 || index >= size)
            return null;
        else {
            Node<E> temp = head;
            for (int i = 0; i < index; i++)
                temp = temp.next;
            E old = temp.element;
            temp.element = e;
            return old;
        } //else
    } //set
    
    /**
     * seeks out an element and returns the index of the element
     * @param e - element sought for
     * @return - integer for the position
     */
    public int indexOf(E e) {
        int k = 0;
        Node<E> temp = head;
        while (temp != null) {
            if (e.equals(temp.element))
                return k;
            temp = temp.next;
            k++;
        } //while
        return -1;
    } //indexOf
    
    /**
     * searches the whole list for an element and returns the index of last appearance
     * @param e - element sought for
     * @return  - index of the last known position
     */
    public int lastIndexOf(E e) {
        int k = -1;
        Node<E> temp = head;
        for (int i = 0; i < size; i++) {
            if (e.equals(temp.element))
                k = i;
            temp = temp.next;
        } //for
        return k;
    } //lastIndexOf
    
    
    private static class Node<E> {
        E element;
        Node<E> next;
        public Node(E element) {
            this.element = element;
        } //constructor
    } //class Node
    
} //class
