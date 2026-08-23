package com.gdufe.lesson10;

public class LeetCode_70 {
    // LeetCode_70 爬楼梯
    public int climbStairs(int n) {
        int i = 0, j = 1;
        for (int k = 0; k < n; ++k) {
            j += i;
            i = j - i;
        }
        return j;
    }

    public static void main(String[] args) {
        LeetCode_70 leetCode_70 = new LeetCode_70();
        System.out.println(leetCode_70.climbStairs(2));
        System.out.println(leetCode_70.climbStairs(3));
    }
}
