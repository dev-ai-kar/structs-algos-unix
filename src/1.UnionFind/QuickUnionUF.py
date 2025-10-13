class QuickUnionUF:
    def __init__(self, N):
        # set id of each object to itself
        self.id = list(range(N))
    
    def root(self, i):
        # chase parent pointers until reach root
        while i != self.id[i]:
            i = self.id[i]
        return i
    
    def connected(self, p, q):
        # check if p and q have same root
        return self.root(p) == self.root(q)
    
    def union(self, p, q):
        # change root of p to point to root of q
        i = self.root(p)
        j = self.root(q)
        self.id[i] = j
