import java.util.Random;

/**
 * A class providing methods to shuffle a singly-linked list in O(n log n) time with O(log n) space.
 */
public class LinkedListShuffler {

    /**
     * Represents a node in a singly-linked list.
     */
    static class ListNode {
        int val;
        ListNode next;

        /** Constructs an empty node */
        ListNode() {}

        /** Constructs a node with given value */
        ListNode(int val) { this.val = val; }

        /** 
         * Constructs a node with given value and next node 
         * @param val Node value
         * @param next Reference to next node
         */
        ListNode(int val, ListNode next) { 
            this.val = val; 
            this.next = next; 
        }
    }

    /**
     * Shuffles a linked list using divide-and-conquer approach
     * @param head Head of the input linked list
     * @return Head of the shuffled linked list
     */
    public static ListNode shuffleLinkedList(ListNode head) {
        int length = getLength(head);
        return shuffleList(head, length);
    }

    /**
     * Calculates length of the linked list
     * @param head Head of the linked list
     * @return Number of nodes in the list
     */
    private static int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    /**
     * Recursively shuffles the list by splitting and merging
     * @param head Head of current sublist
     * @param length Length of current sublist
     * @return Head of shuffled sublist
     */
    private static ListNode shuffleList(ListNode head, int length) {
        if (length <= 1) {
            return head;
        }

        // Split list into two halves
        int leftLength = (length + 1) / 2;
        int rightLength = length / 2;

        ListNode rightHead = split(head, leftLength);
        ListNode shuffledLeft = shuffleList(head, leftLength);
        ListNode shuffledRight = shuffleList(rightHead, rightLength);

        // Merge with random selection
        return merge(shuffledLeft, shuffledRight, leftLength, rightLength);
    }

    /**
     * Splits the list into two parts at specified position
     * @param head Head of list to split
     * @param leftLength Number of nodes to leave in left part
     * @return Head of the right part of the list
     */
    private static ListNode split(ListNode head, int leftLength) {
        if (leftLength == 0) {
            return head;
        }
        ListNode curr = head;
        // Traverse to the split point
        for (int i = 0; i < leftLength - 1; i++) {
            curr = curr.next;
        }
        ListNode rightHead = curr.next;
        curr.next = null; // Break the link between halves
        return rightHead;
    }

    /**
     * Merges two lists by randomly selecting nodes proportionally to their lengths
     * @param left Head of left sublist
     * @param right Head of right sublist
     * @param leftLen Number of nodes in left sublist
     * @param rightLen Number of nodes in right sublist
     * @return Head of merged list
     */
    private static ListNode merge(ListNode left, ListNode right, int leftLen, int rightLen) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;
        ListNode lCurr = left;
        ListNode rCurr = right;
        int lRemain = leftLen;
        int rRemain = rightLen;
        Random random = new Random();

        // Randomly choose nodes while both lists have elements
        while (lRemain > 0 && rRemain > 0) {
            int total = lRemain + rRemain;
            // Choose next node with probability proportional to remaining sizes
            int rand = random.nextInt(total);
            if (rand < lRemain) {
                current.next = lCurr;
                lCurr = lCurr.next;
                lRemain--;
            } else {
                current.next = rCurr;
                rCurr = rCurr.next;
                rRemain--;
            }
            current = current.next;
        }

        // Attach any remaining elements
        current.next = (lRemain > 0) ? lCurr : rCurr;
        return dummy.next;
    }

    /**
     * Helper method to print list values
     * @param head Head of the list to print
     */
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    // Example usage
    public static void main(String[] args) {
        // Create sample list: 1->2->3->4->5
        ListNode head = new ListNode(1, 
            new ListNode(2, 
                new ListNode(3, 
                    new ListNode(4, 
                        new ListNode(5))))); // Fixed missing parenthesis

        System.out.println("Original list:");
        printList(head);

        ListNode shuffledHead = shuffleLinkedList(head);
        System.out.println("Shuffled list:");
        printList(shuffledHead);
    }
}