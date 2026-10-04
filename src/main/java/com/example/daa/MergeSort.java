package com.example.daa;

public class MergeSort {

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) return;
        int[] buffer = new int[a.length];
        sort(a, buffer, 0, a.length - 1, 0, metrics);
    }

    private static void sort(int[] a, int[] buffer, int low, int high, int depth, Metrics metrics) {
        metrics.updateMaxDepth(depth);

        if (high - low <= 15) {
            insertionSort(a, low, high, metrics);
            return;
        }

        int mid = low + (high - low) / 2;
        sort(a, buffer, low, mid, depth + 1, metrics);
        sort(a, buffer, mid + 1, high, depth + 1, metrics);

        metrics.incrementComparisons();
        if (a[mid] <= a[mid + 1]) {
            return;
        }

        merge(a, buffer, low, mid, high, metrics);
    }

    private static void merge(int[] a, int[] buffer, int low, int mid, int high, Metrics metrics) {
        for (int k = low; k <= high; k++) {
            buffer[k] = a[k];
        }

        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                a[k] = buffer[j++];
            } else if (j > high) {
                a[k] = buffer[i++];
            } else {
                metrics.incrementComparisons();
                if (buffer[j] < buffer[i]) {
                    a[k] = buffer[j++];
                } else {
                    a[k] = buffer[i++];
                }
            }
        }
    }

    private static void insertionSort(int[] a, int low, int high, Metrics metrics) {
        for (int i = low + 1; i <= high; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= low) {
                metrics.incrementComparisons();
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    j--;
                } else {
                    break;
                }
            }
            a[j + 1] = key;
        }
    }
}