package com.gdufe.lesson8;

public class LeetCode_242 {
    // LeetCode 242. 有效的字母异位词
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++count[s.charAt(i) - 'a'];
            --count[t.charAt(i) - 'a'];
        }
        for (int i : count) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode_242 leetCode_242 = new LeetCode_242();
        System.out.println(leetCode_242.isAnagram("anagram", "nagaram"));
        System.out.println(leetCode_242.isAnagram("rat", "car"));
    }
}