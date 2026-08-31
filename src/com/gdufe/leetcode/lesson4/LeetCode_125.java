package com.gdufe.leetcode.lesson4;

public class LeetCode_125 {
    // LeetCode 125 验证回文串
    public boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j;) {
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                ++i;
            } else if (!Character.isLetterOrDigit(s.charAt(j))) {
                --j;
            } else if (Character.toLowerCase(s.charAt(i)) == Character.toLowerCase(s.charAt(j))) {
                ++i;
                --j;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode_125 solution = new LeetCode_125();
        String s = "A man, a plan, a canal: Panama";
        boolean result = solution.isPalindrome(s);
        System.out.println("Is palindrome: " + result);
    }
}
