# Assignment 1: Divide and Conquer & Asymptotic Notations
**Author:** Student  
**Course:** Design and Analysis of Algorithms

---

## 1. Asymptotic Bounds Table

| Algorithm | Best Case | Average Case | Worst Case | Brief Explanation (Cause) |
| :--- | :---: | :---: | :---: | :--- |
| **MergeSort** | $\Theta(n \log n)$ | $\Theta(n \log n)$ | $\Theta(n \log n)$ | Always splits array in half and merges in linear time. |
| **QuickSort** | $\Theta(n \log n)$ | $\Theta(n \log n)$ | $\Theta(n \log n)$* | *With random pivot and 3-way partition, even all duplicates or sorted arrays yield $O(n \log n)$ average/worst behavior. |
| **QuickSelect** | $\Theta(n)$ | $\Theta(n)$ | $O(n^2)$ | Worst case occurs if unfortunate pivots are chosen repeatedly (mitigated by random pivot). |
| **Insertion Sort** | $\Theta(n)$ | $\Theta(n^2)$ | $\Theta(n^2)$ | Best case on already sorted data; worst case on reverse-sorted data. Used as a cutoff for small arrays ($\le 15$). |

---

## 2. Recurrence Relations & Master Theorem

### MergeSort
* **Recurrence:** $T(n) = 2T(n/2) + \Theta(n)$
* **Parameters:** $a = 2$, $b = 2$, $f(n) = \Theta(n)$ ($d = 1$)
* **Master Theorem:** Case 2 ($a = b^d \implies 2 = 2^1$)
* **Result:** $\Theta(n \log n)$

### QuickSort (Balanced Split Assumption)
* **Recurrence:** $T(n) = 2T(n/2) + \Theta(n)$
* **Parameters:** $a = 2$, $b = 2$, $f(n) = \Theta(n)$
* **Explanation:** Assuming a balanced split, Master Theorem Case 2 yields $\Theta(n \log n)$. In practice, choosing a random pivot ensures balanced partitions with high probability on average, avoiding $O(n^2)$ degradations.

### QuickSelect (Balanced Split Assumption)
* **Recurrence:** $T(n) = T(n/2) + \Theta(n)$
* **Parameters:** $a = 1$, $b = 2$, $f(n) = \Theta(n)$ ($d = 1$)
* **Master Theorem:** Case 3 ($a < b^d \implies 1 < 2^1$, with regularity condition)
* **Result:** $\Theta(n)$

---

## 3. Discussion & Analysis
* **JVM Warm-up:** The first runs of the algorithms typically show higher execution times due to class loading, JIT compilation, and dynamic optimizations. Running benchmarks 5 times and taking the median effectively mitigates this anomaly.
* **Garbage Collector & Memory:** Implementing MergeSort with a single pre-allocated reusable buffer avoids memory churn and prevents frequent GC pauses inside recursive steps.
* **Cutoff Optimization:** Using Insertion Sort for small subarrays ($\le 15$ elements) reduces recursive overhead and improves cache locality, leading to faster execution times.
* **Comparison with Theory:** The empirical measurements align closely with theoretical expectations. The ratio plots $\text{comparisons} / (n \log_2 n)$ stabilize as $n$ grows large, confirming the tight $\Theta$ bounds.