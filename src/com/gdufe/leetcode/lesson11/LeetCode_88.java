package com.gdufe.leetcode.lesson11;

public class LeetCode_88 {
    // LeetCode_88 合并两个有序数组
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m - 1, j = n - 1; i >= 0 || j >= 0;) {
            if (i >= 0 && (j < 0 || nums1[i] > nums2[j])) {
                nums1[i + j + 1] = nums1[i--];
            } else {
                nums1[i + j + 1] = nums2[j--];
            }
        }
    }

    public static void main(String[] args) {
        LeetCode_88 leetCode_88 = new LeetCode_88();
        int[] nums1 = { 1, 2, 3, 0, 0, 0 };
        int m = 3;
        int[] nums2 = { 2, 5, 6 };
        int n = 3;
        leetCode_88.merge(nums1, m, nums2, n);
        for (int num : nums1) {
            System.out.print(num + " ");
        }
    }
}
