package com.example.daa;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

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
            MergeSort.sort(actual, metrics);

            assertArrayEquals(expected, actual, "Массив отсортирован неверно на итерации " + i);
        }
    }

    @Test
    void testEdgeCases() {
        Metrics metrics = new Metrics();

        int[] empty = {};
        MergeSort.sort(empty, metrics);
        assertArrayEquals(new int[]{}, empty);

        int[] single = {42};
        MergeSort.sort(single, metrics);
        assertArrayEquals(new int[]{42}, single);

        int[] equals = {5, 5, 5, 5, 5};
        MergeSort.sort(equals, metrics);
        assertArrayEquals(new int[]{5, 5, 5, 5, 5}, equals);

        int[] sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        MergeSort.sort(sorted, metrics);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, sorted);
    }
}
