package com.example.daa;

public class Metrics {
    private long comparisons = 0;
    private int maxDepth = 0;

    public void incrementComparisons() {
        comparisons++;
    }

    public long getComparisons() {
        return comparisons;
    }

    public void updateMaxDepth(int depth) {
        if (depth > maxDepth) {
            maxDepth = depth;
        }
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void reset() {
        comparisons = 0;
        maxDepth = 0;
    }
}