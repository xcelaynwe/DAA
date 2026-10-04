package com.example.daa;

import java.util.Random;

public class QuickSelect {
    private static final Random RANDOM = new Random();

    public static int select(int[] a, int k, Metrics metrics) {
        if (a == null || a.length == 0 || k < 0 || k >= a.length) {
            throw new IllegalArgumentException("Некорректный массив или индекс k");
        }
        return select(a, 0, a.length - 1, k, 0, metrics);
    }

    private static int select(int[] a, int low, int high, int k, int depth, Metrics metrics) {
        metrics.updateMaxDepth(depth);

        if (low == high) {
            return a[low];
        }

        int pivotIndex = low + RANDOM.nextInt(high - low + 1);
        int pivot = a[pivotIndex];

        int[] bounds = partition(a, low, high, pivot, metrics);
        int lt = bounds[0];
        int gt = bounds[1];

        if (k >= lt && k <= gt) {
            return a[k];
        } else if (k < lt) {
            return select(a, low, lt - 1, k, depth + 1, metrics);
        } else {
            return select(a, gt + 1, high, k, depth + 1, metrics);
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
