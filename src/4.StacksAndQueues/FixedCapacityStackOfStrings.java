// Description: Implement a fixed-capacity stack of strings using an array.
public class FixedCapacityStackOfStrings {
    private String[] s;
    private int N = 0;

    // getting the capacity of the stack feels like cheating.
    public FixedCapacityStackOfStrings(int capacity) {
        s = new String[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(String item) {
        s[N++] = item;  // this is parsed as s[N] = item; N++; because of the postfix increment operator
    }

    public String pop() {
        // return s[--N];  // this is parsed as N--; return s[N]; because of the prefix decrement operator
        String item = s[--N];
        s[N] = null;  // avoid loitering
        return item;
    }
}
