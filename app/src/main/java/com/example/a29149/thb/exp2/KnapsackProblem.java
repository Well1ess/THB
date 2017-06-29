package com.example.a29149.thb.exp2;

import java.util.Arrays;

/**
 * Created by 张丽华 on 2017/6/13.
 * Description:
 */

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {3, 4, 7, 8, 9};
        int[] v = {4, 5, 10, 11, 13};
        int W = 17;
        int Num = w.length;
        int[][] result = new int[6][18];
        int[] path = new int[Num];

        KnapsackProblem.DP(W, result, v, w);
        KnapsackProblem.findPath(result, path, W, w);

        for (int i = 0; i < 0; i++) {
            println(Arrays.toString(result[i]));
        }

        println(Arrays.toString(path));
        println("————————————————————————————————");

        float[] weight = {2, 4, 3, 1};
        float[] value = {3, 5, 6, 1};
        float[] r = new float[4];

        KnapsackProblem.costPerformance(r, weight, value);
        KnapsackProblem.part(6, value, weight, r);

    }

    public static void DP(int W, int[][] result, int[] value, int[] weight) {
        int n = value.length;
        if (value.length != weight.length)
            throw new IllegalArgumentException("位置对应不对！");

        for (int i = 1; i <= n; i++) {
            result[i][0] = 0;
            for (int w = 1; w <= W; w++) {
                if (weight[i - 1] > w) {
                    result[i][w] = result[i - 1][w];
                } else {
                    int temp = result[i - 1][w - weight[i - 1]] + value[i - 1];
                    if (temp < result[i - 1][w]) {
                        result[i][w] = result[i - 1][w];
                    } else {
                        result[i][w] = temp;
                    }
                }
            }
        }
    }

    public static void findPath(int[][] result, int[] path, int W, int[] weight) {
        int n = weight.length;
        for (int i = n; i >= 2; i--) {
            if (result[i][W] == result[i - 1][W]) {
                path[i - 1] = 0;
            } else {
                W = W - weight[i - 1];
                path[i - 1] = i;
            }
        }

        if (result[1][W] == 0) {
            path[0] = 0;
        } else {
            path[0] = 1;
        }
    }

    public static void costPerformance(float[] r, float[] weight, float[] value) {
        println("按照性价比排序之前");
        println(Arrays.toString(weight));
        println(Arrays.toString(value));

        if (weight.length != value.length) {
            throw new IllegalArgumentException("对应错误！");
        }

        for (int i = 0; i < weight.length; i++) {
            r[i] = value[i] / weight[i];
        }

        println("按照性价比排序之后");
        println(Arrays.toString(weight));
        println(Arrays.toString(value));
        println(Arrays.toString(r));
    }

    public static float part(int W, float[] value, float[] weight, float[] r) {
        int i = value.length - 1;
        float valueSum = 0;
        while (W > 0) {
            if (weight[i] < W) {
                W -= weight[i];
                valueSum += value[i];
                println("第" + i + "号物品，取价值" + value[i] + ",取重为" + weight[i]);
                i--;
            } else {
                println("第" + i + "号物品，取价值" + value[i] + ",取重为" + W);
                W = 0;
            }
        }
        println("总价值为" + valueSum);
        return valueSum;
    }

    public static void QuickSortByKP(float[] w, float[] v, float[] a, int left, int right) {
        if (left >= right)
            return;

        int pos = portitionByKP(a, w, v, left, right);
        QuickSortByKP(w, v, a, left, pos - 1);
        QuickSortByKP(w, v, a, pos + 1, right);
    }

    private static int portitionByKP(float[] a, float[] w, float[] v, int start, int end) {
        float key = a[start];
        float wKey = w[start];
        float vKey = v[start];

        while (start < end) {
            while (start < end && a[end] >= key) {
                end--;
            }

            if (start < end) {
                w[start] = w[end];
                v[start] = v[end];
                a[start++] = a[end];

            }

            while (start < end && key >= a[start]) {
                start++;
            }

            if (start < end) {
                w[end] = w[start];
                v[end] = v[start];
                a[end--] = a[start];
            }
        }
        w[start] = wKey;
        v[start] = vKey;
        a[start] = key;
        return start;
    }

    public static void println(String s) {
        System.out.println(s);
    }
}
