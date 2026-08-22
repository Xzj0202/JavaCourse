package com.gdufe.lesson9;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_169 {
    // LeetCode_169 多数元素
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        for (int num : freq.keySet()) {
            if (freq.get(num) > nums.length / 2) {
                return num;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LeetCode_169 leetCode_169 = new LeetCode_169();
        System.out.println(leetCode_169.majorityElement(new int[] { 3, 2, 3 }));
        System.out.println(leetCode_169.majorityElement(new int[] { 2, 2, 1, 1, 1, 2, 2 }));
    }
}
