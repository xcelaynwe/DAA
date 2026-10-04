package com.example.daa;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class QuickSelectTest {

    @Test
    void testCorrectnessAgainstArraysSort() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int size = random.nextInt(500) + 1;
            int[] original = new int[size];
            for (int j = 0; j < size; j++) {
                original[j] = random.nextInt(2000) - 1000;
            }
            int[] expected = original.clone();
            Arrays.sort(expected);

            int k = random.nextInt(size);
            int actual = QuickSelect.select(original.clone(), k, new Metrics());

            assertEquals(expected[k], actual, "Ошибка при поиске k = " + k);
        }
    }

    @Test
    void testEdgeCasesAndExceptions() {
        Metrics metrics = new Metrics();

        int[] empty = {};
        assertThrows(IllegalArgumentException.class, () -> {
            QuickSelect.select(empty, 0, metrics);
        });

        int[] arr = {10, 20, 30};
        assertThrows(IllegalArgumentException.class, () -> {
            QuickSelect.select(arr, -1, metrics);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            QuickSelect.select(arr, 5, metrics);
        });

        int[] single = {42};
        assertEquals(42, QuickSelect.select(single, 0, metrics));
    }
}