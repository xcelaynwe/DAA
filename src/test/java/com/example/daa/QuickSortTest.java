package com.example.daa;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void testCorrectnessAgainstArraysSort() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int size = random.nextInt(1000) + 1;
            int[] original = new int[size];
            for (int j = 0; j < size; j++) {
                original[j] = random.nextInt(2000) - 1000;
            }
            int[] expected = original.clone();
            Arrays.sort(expected);
            int[] actual = original.clone();
            Metrics metrics = new Metrics();
            QuickSort.sort(actual, metrics);

            assertArrayEquals(expected, actual, "Ошибка на итерации " + i);
        }
    }

    @Test
    void testEdgeCases() {
        Metrics metrics = new Metrics();

        int[] empty = {};
        QuickSort.sort(empty, metrics);
        assertArrayEquals(new int[]{}, empty);

        int[] single = {42};
        QuickSort.sort(single, metrics);
        assertArrayEquals(new int[]{42}, single);

        int[] equals = {5, 5, 5, 5, 5};
        QuickSort.sort(equals, metrics);
        assertArrayEquals(new int[]{5, 5, 5, 5, 5}, equals);

        int[] sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        QuickSort.sort(sorted, metrics);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, sorted);
    }

    @Test
    void testRecursionDepthOnSortedArray() {
        int n = 100000;
        int[] sorted = new int[n];
        for (int i = 0; i < n; i++) {
            sorted[i] = i;
        }

        Metrics metrics = new Metrics();
        QuickSort.sort(sorted, metrics);

        int maxAllowedDepth = (int) (2 * Math.ceil(Math.log(n) / Math.log(2)));
        assertTrue(metrics.getMaxDepth() <= maxAllowedDepth,
                "Глубина рекурсии слишком большая: " + metrics.getMaxDepth());
    }
}