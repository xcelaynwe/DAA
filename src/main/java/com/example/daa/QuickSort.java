package com.example.daa;

import java.util.Random;

public class QuickSort {
    private static final Random RANDOM = new Random();

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) return;
        sort(a, 0, a.length - 1, 0, metrics);
    }

    private static void sort(int[] a, int low, int high, int depth, Metrics metrics) {
        metrics.updateMaxDepth(depth);

        while (low < high) {
            int pivotIndex = low + RANDOM.nextInt(high - low + 1);
            int pivot = a[pivotIndex];

            int[] bounds = partition(a, low, high, pivot, metrics);
            int lt = bounds[0];
            int gt = bounds[1];

            if (lt - low < high - gt) {
                sort(a, low, lt - 1, depth + 1, metrics);
                low = gt + 1;
            } else {
                sort(a, gt + 1, high, depth + 1, metrics);
                high = lt - 1;
            }
        }
    }

    private static int[] partition(int[] a, int low, int high, int pivot, Metrics metrics) {
        int lt = low, gt = high;
        int i = low;
        while (i <= gt) {
            metrics.incrementComparisons();
            if (a[i] < pivot) {
                swap(a, lt++, i++);
            } else if (a[i] > pivot) {
                swap(a, i, gt--);
            } else {
                i++;
            }
        }
        return new int[] { lt, gt };
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}