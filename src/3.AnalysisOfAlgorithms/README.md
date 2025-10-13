QUESTION
---

**Egg Drop Problem**  
You have an \( n \)-story building (floors \( 1 \) through \( n \)) and plenty of eggs. An egg breaks if dropped from floor \( T \) or higher, and survives otherwise. Devise strategies to determine \( T \) under the following constraints:

**Version 0**:  
- \( 1 \) egg.  
- \( \leq T \) tosses (worst case \( \leq n \) tosses).  

**Version 1**:  
- \( \sim 1 \log n \) eggs.  
- \( \sim 1 \log n \) tosses.  

**Version 2**:  
- \( \sim \log T \) eggs.  
- \( \sim 2 \log T \) tosses.  

**Version 3**:  
- \( 2 \) eggs.  
- \( \sim 2 \sqrt{n} \) tosses.  

**Version 4**:  
- \( 2 \) eggs.  
- \( \leq c \sqrt{T} \) tosses for some fixed constant \( c \).  

For each version, describe the optimal strategy.  


ANSWER
---

**Version 0:** With 1 egg, perform a linear search starting from floor 1 upwards until the egg breaks. This requires up to **T tosses** (worst-case **n** tosses).

**Version 1:** Use **binary search**. Start at the middle floor, adjust up or down based on whether the egg breaks, repeating until T is found. Uses **O(log n) eggs** and **O(log n) tosses**.

**Version 2:** First perform an **exponential search** (floors 1, 2, 4, ..., 2^k) until the egg breaks. Then conduct a **binary search** in the identified range. Requires **O(log T) eggs** and **O(log T) tosses** for each phase, totaling **O(2 log T)** tosses.

**Version 3:** Divide the building into intervals of size **√n**. Drop the first egg from each interval floor (√n, 2√n, ...). If it breaks, linearly check each floor in the previous interval with the second egg. Uses **2 eggs** and **O(√n) tosses**.

**Version 4:** Use a **decreasing increment strategy**. Drop the first egg from floors following the sequence 1, 3, 6, 10, ... (each step increases by one less than the previous). Once the egg breaks, perform a linear search in the prior interval. This ensures **O(√T) tosses** with **2 eggs** and a constant factor **c**.

---

**Final Answer**

Version 0: \boxed{Linear search from floor 1 upwards.}  
Version 1: \boxed{Binary search.}  
Version 2: \boxed{Exponential search followed by binary search.}  
Version 3: \boxed{Interleaving intervals of size \sqrt{n}.}  
Version 4: \boxed{Decreasing step strategy with linear search.}