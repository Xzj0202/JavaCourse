package com.gdufe.leetcode.lesson8;

public class LeetCode_7 {
    // LeetCode 7. 整数反转
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int pop = x % 10;
            x /= 10;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        LeetCode_7 leetCode_7 = new LeetCode_7();
        System.out.println(leetCode_7.reverse(123));
        System.out.println(leetCode_7.reverse(-123));
        System.out.println(leetCode_7.reverse(120));
        System.out.println(leetCode_7.reverse(0));
    }
}
