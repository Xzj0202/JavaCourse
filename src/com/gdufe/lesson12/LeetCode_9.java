package com.gdufe.lesson12;

public class LeetCode_9 {
    // LeetCOde_9 回文数
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) {
                return false;
            }
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode_9 leetCode_9 = new LeetCode_9();
        System.out.println(leetCode_9.isPalindrome(121));
        System.out.println(leetCode_9.isPalindrome(-121));
        System.out.println(leetCode_9.isPalindrome(10));
    }
}
