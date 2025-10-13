// How to grow array?
// A.  If array is full, create a new array of twice the size, and copy items.
// Consequence.  Inserting first N items takes time proportional to N (not N 2 ).
// Stack:  resizing-array implementation using "repeated doubling" 
public class ResizingArrayStackOfStrings {
    private String[] s;
    private int N = 0;

    public ResizingArrayStackOfStrings() {
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

    // now lets think how to shrink the array
    // one might think if we doubled the size when array is full, we should halve the size when array is one-halfed
    // but this is not a good idea because it will lead to thrashing
    // thrashing is the repeated doubling and halving of the array
    // so if we have continuously alternating push and pop operations, the array will keep on resizing
    // so we will resize the array to half when it is one-quarter full

    public String pop() {
        // return s[--N]; // this is parsed as N--; return s[N]; because of the prefix
        // decrement operator
        String item = s[--N];
        s[N] = null; // avoid loitering
        if (N > 0 && N == s.length / 4)
            resize(s.length / 2);
        return item;
    }
}