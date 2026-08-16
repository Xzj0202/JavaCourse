package com.gdufe.lesson3;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;

public class LeetCode_349 {
    // LeetCode_349 两个数组的交集
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> st = new HashSet<>();
        for (int x : nums1) {
            st.add(x);
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for (int x : nums2) {
            if (st.contains(x)) {
                ans.add(x);
                st.remove(x);
            }
        }

        int[] result = new int[ans.size()];
        for (int i = 0; i < ans.size(); ++i) {
            result[i] = ans.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode_349 solution = new LeetCode_349();

        int[] nums1 = { 1, 2, 2, 1 };
        int[] nums2 = { 2, 2 };
        int[] result = solution.intersection(nums1, nums2);
        System.out.println(Arrays.toString(result));
    }
}