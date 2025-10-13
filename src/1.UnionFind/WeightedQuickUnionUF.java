// This is a lazy approach to Quick Union // union find problems
public class WeightedQuickUnionUF
{
    private int[] id;
    private int[] sz;

    public WeightedQuickUnionUF(int N)
    {
        // set id of each object to itself ( N array accesses)
        id = new int[N];
        for (int i = 0; i < N; i++)
        {
            id[i] = i;
            sz[i] = 1; // improvement 1
        }

    }

    private int root(int i)
    {
        // chase parent pointers until reach root (depth of i array accesses)
        while (i!= id[i])
        {
            id[i] = id[id[i]]; // this improvement is only one line
            i = id[i];

        }
        return i;
    }

    public boolean connected(int p, int q)
    {
        // check if p and q have same root (depth of p and q array accesses)
        return root(p) == root(q);
    }

    public void union(int p, int q)
    {
        // change root of p to point to root of q (depth of p and q array accesses)
        int i = root(p);
        int j = root(q);
        //[i] = j;
        // Increase efficiency by linking root of smaller tree to root of larger tree and add sz to the mix
        if(i==j) return;
        // if tree i is smaller than tree j, then add tree i to tree j
        if(sz[i]<sz[j]){id[i] = j; sz[j] += sz[i];}
        else {id[j] = i; sz[i] += sz[j];}
    }
}
