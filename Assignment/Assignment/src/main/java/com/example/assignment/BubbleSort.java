package com.example.assignment;

public class BubbleSort {
    public static void sort(Finance[] finances) {
        int n = finances.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (finances[j].getMonth().compareTo(finances[j + 1].getMonth()) > 0) {
                    Finance temp = finances[j];
                    finances[j] = finances[j + 1];
                    finances[j + 1] = temp;
                }
            }
        }
    }
}