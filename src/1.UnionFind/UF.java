public class UF {
    private int[] id;    // id[i] = component identifier of i
    private int count;   // number of components

    public UF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        // Nothing to do if p and q are already in the same component
        if (pID == qID) return;

        // Rename p's component to q's name
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) id[i] = qID;
        }
        count--;
    }

    public int find(int p) {
        return id[p];
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }
}
