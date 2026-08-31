package com.gdufe.leetcode.lesson11;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_118 {
    // LeetCode——118 杨辉三角
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; ++i) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
                }
            }
            ans.add(row);
        }
        return ans;
    }

    public static void main(String[] args) {
        LeetCode_118 leetCode_118 = new LeetCode_118();
        List<List<Integer>> ans = leetCode_118.generate(5);
        for (List<Integer> row : ans) {
            for (Integer num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
