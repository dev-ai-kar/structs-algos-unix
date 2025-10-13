class WeightedQuickUnionUF:
    def __init__(self, N):
        self.id = list(range(N))
        self.sz = [1] * N

    def _root(self, i):
        while i != self.id[i]:
            # Path compression: make every node in path point to its grandparent
            self.id[i] = self.id[self.id[i]]
            i = self.id[i]
        return i

    def connected(self, p, q):
        return self._root(p) == self._root(q)

    def union(self, p, q):
        i = self._root(p)
        j = self._root(q)
        if i == j:
            return
        # Attach smaller tree to larger tree
        if self.sz[i] < self.sz[j]:
            self.id[i] = j
            self.sz[j] += self.sz[i]
        else:
            self.id[j] = i
            self.sz[i] += self.sz[j]

if __name__ == '__main__':
    uf = WeightedQuickUnionUF(10)
    uf.union(4, 3)
    uf.union(3, 8)
    uf.union(6, 5)
    uf.union(9, 4)
    uf.union(2, 1)
    uf.union(5, 0)
    uf.union(7, 2)
    uf.union(6, 1)
    uf.union(7, 3)
    print(uf.id)  # [6, 2, 6, 4, 6, 6, 6, 2, 4, 4]
    