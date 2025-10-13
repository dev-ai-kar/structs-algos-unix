// Linked List implementation of Stack
// This is a generic implementation of a stack using a linked list. 
// The linked list is a data structure that consists of a sequence of elements, 
// each containing a reference to the next element in the sequence. 
// Stack.java

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {

    // Linked List implementation
    private Node first = null;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            // not needed
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // Stack test client
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(stack.pop());
            else
                stack.push(s);
        }
        StdOut.println(" (" + stack.first.item + " left on stack)");
    }
    // % more tobe.txt
    // to be or not to - be - - that - - - is

    // % java Stack < tobe.txt
    // to be not that or be (2 left on stack)
}

/*
 * public class Stack
 * {
 * Stack() // create an empty stack
 * void push(Item item) // insert a new string onto stack
 * Item pop() // remove and return the string most recently added
 * boolean isEmpty() // is the stack empty?
 * }
 */