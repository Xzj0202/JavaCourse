package com.gdufe.lesson5;

public class LeetCode_344 {
    // LeetCode 344 反转字符串
    public void reverseString(char[] s) {
        for (int l = 0, r = s.length - 1; l < r; ++l, --r) {
            s[l] ^= s[r];
            s[r] ^= s[l];
            s[l] ^= s[r];
        }
    }

    public static void main(String args[]) {
        LeetCode_344 leetCode_344 = new LeetCode_344();
        char[] s = { 'h', 'e', 'l', 'l', 'o' };
        leetCode_344.reverseString(s);
        System.out.println(s);
    }
}
