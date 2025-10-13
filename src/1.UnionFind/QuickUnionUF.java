// This is a lazy approach to Quick Union // union find problems
//      - key is the node name and the value is the root/compound group name
public class QuickUnionUF
{
    private int[] id;

    public QuickUnionUF(int N)
    {
        // set id of each object to itself ( N array accesses)
        id = new int[N];
        for (int i = 0; i < N; i++) id[i] = i;

    }

    private int root(int i)
    {
        // chase parent pointers until reach root (depth of i array accesses)
        while (i!= id[i]) i = id[i];
        return i;
    }

    // Find. Check if p and q have same root (depth of p and q array accesses)
    public boolean connected(int p, int q)
    {
        // check if p and q have same root (depth of p and q array accesses)
        return root(p) == root(q);
    }

    // Union. To merge components containing p and q, 
    // set the id of p's root to the id of q's root (depth of p and q array accesses)
    public void union(int p, int q)
    {
        // change root of p to point to root of q (depth of p and q array accesses)
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }
}
