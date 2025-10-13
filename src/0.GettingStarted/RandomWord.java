import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * A program that reads a sequence of words from standard input and prints one of those
 * words uniformly at random. This implementation uses reservoir sampling algorithm to
 * ensure each word has an equal probability of being selected.
 * <p>
 * Compilation: javac -cp ".;lib/algs4.jar" src/0.GettingStarted/RandomWord.java -d build
 * Execution cmd: java -cp ".;build;lib/algs4.jar" RandomWord < data/animals8.txt
 * Execution pwsh: Get-Content .\data\animals8.txt | java -cp ".;build;lib\algs4.jar" RandomWord
 * <p>
 * press Ctrl+Z (Windows) or Ctrl+D (WSL/Linux) to signal end-of-input
 * <p>
 * java-algs4 RandomWord < animals8.txt
 *
 * @author Your Name
 * @version 1.0
 */
public class RandomWord {
    public static void main(String[] args) {
        // Champion holds the currently selected word based on reservoir sampling
        String champion = "";

        // i represents the count of words processed so far
        int i = 1;

        // Process each word from standard input using reservoir sampling
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            // For the i-th word, select it as the new champion with probability 1/i
            if (StdRandom.bernoulli(1.0 / i)) {
                champion = word;
            }
            i++;
        }

        // Output the final selected word
        StdOut.println(champion);
    }
}
