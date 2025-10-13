// Linked List implementation of StackOfStrings
// StackOfStrings.java

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StackOfStrings {

    // Linked List implementation
    private Node first = null;

    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(String item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public String pop() {
        String item = first.item;
        first = first.next;
        return item;
    }

    // Stack test client
    public static void main(String[] args) {
        StackOfStrings stack = new StackOfStrings();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(stack.pop());
            else
                stack.push(s);
        }
    }
    // % more tobe.txt
    // to be or not to - be - - that - - - is

    // % java StackOfStrings < tobe.txt
    // to be not that or be (2 left on stack)
}

/*
 * public class StackOfStrings
 * {
 * StackOfStrings() // create an empty stack
 * void push(String item) // insert a new string onto stack
 * String pop() // remove and return the string most recently added
 * boolean isEmpty() // is the stack empty?
 * }
 */