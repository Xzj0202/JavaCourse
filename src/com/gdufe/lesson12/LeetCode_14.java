package com.gdufe.lesson12;

public class LeetCode_14 {
    // LeetCode_14 最长公共前缀
    // public String longestCommonPrefix(String[] strs) {
    // if (strs == null || strs.length == 0) {
    // return "";
    // }
    // String prefix = strs[0];
    // for (int i = 1; i < strs.length; ++i) {
    // while(strs[i].startsWith(prefix)==false){
    // prefix = prefix.substring(0, prefix.length() - 1);
    // if (prefix.isEmpty()) {
    // return "";
    // }
    // }
    // }
    // return prefix;
    // }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        StringBuilder prefix = new StringBuilder(strs[0]);
        for (int i = 1; i < strs.length; ++i) {
            while (strs[i].indexOf(prefix.toString()) != 0) {
                prefix.deleteCharAt(prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix.toString();
    }

    public static void main(String[] args) {
        LeetCode_14 leetCode_14 = new LeetCode_14();
        String[] strs = { "flower", "flow", "flight" };
        System.out.println(leetCode_14.longestCommonPrefix(strs));
    }
}
