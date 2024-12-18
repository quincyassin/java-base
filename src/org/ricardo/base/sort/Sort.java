package org.ricardo.base.sort;

import java.util.Arrays;

public class Sort {

    public static void main(String[] args) {
        String square = "b5";
        System.out.println((square.charAt(0) - square.charAt(1)) & 1);


        int[] a = { 5, 4, 3, 2, 3 };
        System.out.println(Arrays.toString(sort(a)));
    }

    public static int[] sort(int[] a) {
        if (a == null || a.length == 0) {
            return a;
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[i]) {
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
        return a;
    }
}
