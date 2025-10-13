
class MaxStack:
    def __init__(self):
        self.main_stack = []
        self.max_stack = []

    def push(self, x: float) -> None:
        self.main_stack.append(x)
        if not self.max_stack or x >= self.max_stack[-1]:
            self.max_stack.append(x)

    def pop(self) -> float:
        if not self.main_stack:
            raise IndexError("pop from empty stack")
        popped = self.main_stack.pop()
        if popped == self.max_stack[-1]:
            self.max_stack.pop()
        return popped

    def get_max(self) -> float:
        if not self.max_stack:
            raise IndexError("get_max on empty stack")
        return self.max_stack[-1]
