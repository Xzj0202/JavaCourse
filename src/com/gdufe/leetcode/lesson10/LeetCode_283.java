package com.gdufe.leetcode.lesson10;

public class LeetCode_283 {
    // LeetCode_283 移动零
    public void moveZeroes(int[] nums) {
        int p = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                swap(nums, p++, i);
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }

    public static void main(String[] args) {
        LeetCode_283 leetCode_283 = new LeetCode_283();
        int[] nums1 = { 0, 1, 0, 3, 12 };
        leetCode_283.moveZeroes(nums1);
        for (int num : nums1) {
            System.out.print(num + " ");
        }
        System.out.println();

        int[] nums2 = { 0 };
        leetCode_283.moveZeroes(nums2);
        for (int num : nums2) {
            System.out.print(num + " ");
        }
        System.out.println();

        int[] nums3 = { 1, 0, 2 };
        leetCode_283.moveZeroes(nums3);
        for (int num : nums3) {
            System.out.print(num + " ");
        }
    }
}
