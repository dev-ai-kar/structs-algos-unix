class QuickFindUF:
    def __init__(self, n: int):
        """Set id of each object to itself (N array accesses)"""
        self._id = list(range(n))
    
    def connected(self, p: int, q: int) -> bool:
        """Check whether p and q are in the same component (2 array accesses)"""
        return self._id[p] == self._id[q]
    
    def union(self, p: int, q: int) -> None:
        """Change all entries with id[p] to id[q] (at most 2N + 2 array accesses)"""
        pid = self._id[p]
        qid = self._id[q]
        for i in range(len(self._id)):
            if self._id[i] == pid:
                self._id[i] = qid
