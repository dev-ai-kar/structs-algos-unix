#include <stack>

using namespace std;

class MyQueue {
private:
    stack<int> enqueue_stack;
    stack<int> dequeue_stack;

    void transferElements() {
        if (dequeue_stack.empty()) {
            while (!enqueue_stack.empty()) {
                int element = enqueue_stack.top();
                enqueue_stack.pop();
                dequeue_stack.push(element);
            }
        }
    }

public:
    MyQueue() {}

    void push(int x) {
        enqueue_stack.push(x);
    }

    int pop() {
        transferElements();
        int val = dequeue_stack.top();
        dequeue_stack.pop();
        return val;
    }

    int peek() {
        transferElements();
        return dequeue_stack.top();
    }

    bool empty() {
        return enqueue_stack.empty() && dequeue_stack.empty();
    }
};