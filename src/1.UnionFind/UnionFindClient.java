/**
 * Union-Find client that reads from standard input.
 * 
 * Compilation: javac -cp ".;lib/algs4.jar" src/1.UnionFind/UnionFindClient.java src/1.UnionFind/UF.java -d build
 * Execution: java -cp ".;build;lib/algs4.jar" UnionFindClient < data/tinyUF.txt
 * 
 * PowerShell:
 * # Compile
 * PS> javac -cp ".;lib/algs4.jar" src/1.UnionFind/UnionFindClient.java src/1.UnionFind/UF.java -d build
 * 
 * # Run with file input
 * PS> Get-Content ./data/tinyUF.txt | java -cp ".;build;lib/algs4.jar" UnionFindClient
 * 
 * Note: Use : instead of ; in classpath for Unix/Linux systems
 */
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.UF;

public class UnionFindClient {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        int N = StdIn.readInt();
        UF uf = new UF(N); 
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(p + "<--->" + q);
            }
        }
    }
}