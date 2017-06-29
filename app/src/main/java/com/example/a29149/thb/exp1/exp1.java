package com.example.a29149.thb.exp1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by 张丽华 on 2017/6/13.
 * Description:
 */

public class exp1 {
    public static void main(String[] args) {
        int[] a = {3, 4, 7, 8, 9};
        println("Random QuickSort Please input 1 or input 2 select QuickSort");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n != 1 && n != 2) {
            println("input error");
        }

        QuickSort(a, 0, a.length - 1, n == 1);
        println(Arrays.toString(a));
    }

    private static void QuickSort(int[] a, int left, int right, boolean b) {
        if (left >= right)
            return;

        int pos = portition(a, left, right, b);
        QuickSort(a, left, pos - 1, b);
        QuickSort(a, pos + 1, right, b);
    }

    private static int portition(int[] a, int start, int end, boolean b) {
        if (b) {
            int randomPos = random(start, end);
            int temp = a[randomPos];
            a[randomPos] = a[start];
            a[start] = temp;
        }

        int key = a[start];

        while (start < end) {
            while (start < end && a[end] >= key) {
                end--;
            }

            if (start < end) {
                a[start++] = a[end];
            }

            while (start < end && key >= a[start]) {
                start++;
            }

            if (start < end) {
                a[end--] = a[start];
            }
        }

        a[start] = key;
        return start;
    }

    private static int random(int start, int end) {
        Random r = new Random();
        return r.nextInt(end - start) + start;
    }

    public static void println(String s) {
        System.out.println(s);
    }
}
