// Array implementation of Stack
// This is a generic implementation of a stack using a linked list. 
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ArrayStack {

    private String[] s;
    private int N = 0;

    public ArrayStack() {
        s = new String[1];
    }

    public void push(String item) {
        if (N == s.length)
            resize(2 * s.length);
        s[N++] = item;
    }

    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++)
            copy[i] = s[i];
        s = copy;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public String pop() {
        String item = s[--N];
        s[N] = null; // avoid loitering
        if (N > 0 && N == s.length / 4)
            resize(s.length / 2);
        return item;
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(stack.pop());
            else
                stack.push(s);
        }
    }
}