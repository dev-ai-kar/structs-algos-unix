#include <vector>

class WeightedQuickUnionUF {
private:
    std::vector<int> id;
    std::vector<int> sz;

    int root(int i) {
        while (i != id[i]) {
            // Path compression: make node point to its grandparent
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

public:
    WeightedQuickUnionUF(int N) {
        id.resize(N);
        sz.resize(N, 1);
        for (int i = 0; i < N; ++i) {
            id[i] = i;
        }
    }

    bool connected(int p, int q) {
        return root(p) == root(q);
    }

    void Union(int p, int q) {  // Renamed from 'union' (which is a keyword)
        int i = root(p);
        int j = root(q);
        if (i == j) return;

        // Attach smaller tree to larger tree
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        }
        else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }
};

int main() {
    WeightedQuickUnionUF uf(10);
    uf.Union(4, 3);
    uf.Union(3, 8);
    uf.Union(6, 5);
    uf.Union(9, 4);

    bool connected = uf.connected(8, 9); // Should be true
    connected = uf.connected(5, 0);      // Should be false

    return 0;
}