package com.gdufe.lesson9;

public class LeetCode_136 {
    // LeetCode 136. 只出现一次的数字
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }

    public static void main(String[] args) {
        LeetCode_136 leetCode_136 = new LeetCode_136();
        System.out.println(leetCode_136.singleNumber(new int[] { 2, 2, 1 }));
        System.out.println(leetCode_136.singleNumber(new int[] { 4, 1, 2, 1, 2 }));
        System.out.println(leetCode_136.singleNumber(new int[] { 1 }));
    }
}
