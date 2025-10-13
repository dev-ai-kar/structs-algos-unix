/*
 * Title: Board.java
 * 
 * Compilation:
 * javac -cp ".;lib/algs4.jar" src/9.PriorityQueues/Board.java -d build
 * 
 * Note: Board.java is typically compiled together with Solver.java as they work together.
 * No separate execution command needed as Board is used by Solver.
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;


public class Board {

    // construct a board from an n-by-n array of tiles
    // (where tiles[row][col] = tile at (row, col))
    private final int n;
    private final int[][] tiles;
    private final int hamming;
    private final int manhattan;


    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles){
        if (tiles == null) throw new IllegalArgumentException("Tiles cannot be null");
        n = tiles.length;
        this.tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
                this.tiles[i] = Arrays.copyOf(tiles[i], n);
        }
        hamming = computeHamming();
        manhattan = computeManhattan();
    }
                                           
    // string representation of this board
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(n).append("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(tiles[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension(){
        return n;
    }

    // number of tiles out of place
    public int hamming(){
        return hamming;
    }

    // helper method to compute Hamming distance
    private int computeHamming() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != i * n + j + 1) {
                    count++;
                }
            }
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan(){
        return manhattan;
    }

    // helper method to compute Manhattan distance
    private int computeManhattan() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != 0) {
                    int targetRow = (tiles[i][j] - 1) / n;
                    int targetCol = (tiles[i][j] - 1) % n;
                    sum += Math.abs(i - targetRow) + Math.abs(j - targetCol);
                }
            }
        }
        return sum;
    }

    // is this board the goal board?
    public boolean isGoal(){
        return hamming == 0;
    }

    // does this board equal y?
    public boolean equals(Object y){
        if (y == this) return true;
        if (y == null || y.getClass() != this.getClass()) return false;
        Board other = (Board) y;
        if (this.n != other.n) return false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != other.tiles[i][j]) return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors(){
        List<Board> neighbors = new ArrayList<>();
        int zeroRow = -1, zeroCol = -1;

        // Find the position of the empty tile (0)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                    break;
                }
            }
            if (zeroRow != -1) break; // Exit if we found the zero tile
        }

        // Possible moves: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int newRow = zeroRow + dir[0];
            int newCol = zeroCol + dir[1];
            if (isValid(newRow, newCol)) {
                int[][] newTiles = copyTiles();
                swap(newTiles, zeroRow, zeroCol, newRow, newCol);
                neighbors.add(new Board(newTiles));
            }
        }
        return neighbors;
    }

    // Helper method to check if a position is valid
    private boolean isValid(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }

    // Helper method to create a copy of the tiles array
    private int[][] copyTiles() {
        int[][] newTiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            newTiles[i] = Arrays.copyOf(tiles[i], n);
        }
        return newTiles;
    }

    // Helper method to swap two tiles in the tiles array
    private void swap(int[][] tiles, int row1, int col1, int row2, int col2) {
        int temp = tiles[row1][col1];
        tiles[row1][col1] = tiles[row2][col2];
        tiles[row2][col2] = temp;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] twinTiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            twinTiles[i] = Arrays.copyOf(tiles[i], n);
        }
        int i1 = -1, j1 = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (twinTiles[i][j] != 0) {
                    if (i1 == -1) {
                        i1 = i;
                        j1 = j;
                    } else {
                        int temp = twinTiles[i1][j1];
                        twinTiles[i1][j1] = twinTiles[i][j];
                        twinTiles[i][j] = temp;
                        return new Board(twinTiles);
                    }
                }
            }
        }
        return new Board(twinTiles);
    }

    // unit testing (not graded){}
    public static void main(String[] args){}

}