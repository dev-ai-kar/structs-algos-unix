/// This is an Eager approach to quickfind
#include <iostream>
#include <vector>

class QuickFindUF {
private:
    std::vector<int> id;

public:
    // set id of each object to itself (N array accesses)
    QuickFindUF(int n) {
        id.resize(n);
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    // check whether p and q are in the same component (2 arrays accesses)
    bool connected(int p, int q) {
        return id[p] == id[q];
    }

    // change all entries with id[p] to id[q]
    // (at most 2N + 2 arrays accesses)
    void union_(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.size(); i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }
};