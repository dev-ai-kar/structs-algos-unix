import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStack<Item> {
    private Item[] s;
    private int N = 0;

    @SuppressWarnings("unchecked")
    public FixedCapacityStack(int capacity) {
        // s = new Item[capacity];
        s = (Item[]) new Object[capacity]; // Java does not allow generic array creation. so we use ugly casting
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(Item item) {
        s[N++] = item;
    }

    public Item pop() {
        return s[--N];
    }

    public static void main(String[] args) {
        FixedCapacityStack<String> stack = new FixedCapacityStack<String>(100);
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

    // % java FixedCapacityStack < tobe.txt
    // to be not that or be (2 left on stack)
    
}
