package com.gdufe.lesson3;

public class LeetCode_342 {
    // LeetCode_342 4的幂
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        while (n > 1) {
            if (n % 4 != 0) {
                return false;
            }
            n >>= 2;
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode_342 solution = new LeetCode_342();

        System.out.println(solution.isPowerOfFour(16));
        System.out.println(solution.isPowerOfFour(5));
    }
}
