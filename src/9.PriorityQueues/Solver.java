/*
 * Title: Solver.java
 * 
 * Compilation:
 * 
 javac -cp ".;lib/algs4.jar" src/9.PriorityQueues/Board.java src/9.PriorityQueues/Solver.java -d build
 * 
 * Execution cmd:
 java -cp ".;build;lib/algs4.jar" Solver data/puzzle04.txt
 * 
 * Execution pwsh:
 java -cp ".;build;lib\algs4.jar" Solver .\data\puzzle04.txt
 */

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;

public class Solver {
    private boolean solvable;
    private int moves;
    private LinkedList<Board> solution;

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        MinPQ<Node> originalPQ = new MinPQ<>();
        MinPQ<Node> twinPQ = new MinPQ<>();
        originalPQ.insert(new Node(initial, 0, null));
        twinPQ.insert(new Node(initial.twin(), 0, null));

        while (true) {
            if (!originalPQ.isEmpty()) {
                Node current = originalPQ.delMin();
                if (current.board.isGoal()) {
                    solvable = true;
                    moves = current.moves;
                    solution = new LinkedList<>();
                    Node node = current;
                    while (node != null) {
                        solution.addFirst(node.board);
                        node = node.prev;
                    }
                    break;
                }
                for (Board neighbor : current.board.neighbors()) {
                    if (current.prev == null || !neighbor.equals(current.prev.board)) {
                        originalPQ.insert(new Node(neighbor, current.moves + 1, current));
                    }
                }
            }

            if (!twinPQ.isEmpty()) {
                Node currentTwin = twinPQ.delMin();
                if (currentTwin.board.isGoal()) {
                    solvable = false;
                    moves = -1;
                    solution = null;
                    break;
                }
                for (Board neighbor : currentTwin.board.neighbors()) {
                    if (currentTwin.prev == null || !neighbor.equals(currentTwin.prev.board)) {
                        twinPQ.insert(new Node(neighbor, currentTwin.moves + 1, currentTwin));
                    }
                }
            }

            if (originalPQ.isEmpty() && twinPQ.isEmpty()) {
                solvable = false;
                moves = -1;
                solution = null;
                break;
            }
        }
    }

    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        return moves;
    }

    public Iterable<Board> solution() {
        return solution;
    }

    private class Node implements Comparable<Node> {
        private final Board board;
        private final int moves;
        private final Node prev;
        private final int priority;

        public Node(Board board, int moves, Node prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            this.priority = moves + board.manhattan();
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.priority, other.priority);
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        Board initial = new Board(tiles);
        Solver solver = new Solver(initial);
        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
        } else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }
    }
}