package com.example.daa;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 100000, 1000000};
        String[] inputTypes = {"random", "sorted", "duplicates"};
        String[] algorithms = {"MergeSort", "QuickSort", "QuickSelect"};

        try (PrintWriter writer = new PrintWriter(new FileWriter("results.csv"))) {
            writer.println("algorithm,input,n,time_ms,comparisons,max_depth");

            for (int n : sizes) {
                for (String inputType : inputTypes) {
                    int[] baseArray = generateArray(n, inputType);

                    for (String algo : algorithms) {
                        long[] times = new long[5];
                        long comparisons = 0;
                        int maxDepth = 0;

                        for (int run = 0; run < 5; run++) {
                            int[] arr = baseArray.clone();
                            Metrics metrics = new Metrics();

                            long start = System.nanoTime();
                            if (algo.equals("MergeSort")) {
                                MergeSort.sort(arr, metrics);
                            } else if (algo.equals("QuickSort")) {
                                QuickSort.sort(arr, metrics);
                            } else if (algo.equals("QuickSelect")) {
                                QuickSelect.select(arr, n / 2, metrics);
                            }
                            long end = System.nanoTime();

                            times[run] = end - start;
                            if (run == 4) {
                                comparisons = metrics.getComparisons();
                                maxDepth = metrics.getMaxDepth();
                            }
                        }

                        Arrays.sort(times);
                        double medianTimeMs = times[2] / 1_000_000.0;

                        writer.printf("%s,%s,%d,%.3f,%d,%d\n",
                                algo, inputType, n, medianTimeMs, comparisons, maxDepth);
                    }
                }
            }
            System.out.println("Бенчмарк успешно завершен! Файл results.csv создан.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] generateArray(int n, String type) {
        Random random = new Random(42);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            if (type.equals("random")) {
                a[i] = random.nextInt();
            } else if (type.equals("sorted")) {
                a[i] = i;
            } else if (type.equals("duplicates")) {
                a[i] = random.nextInt(10);
            }
        }
        return a;
    }
}