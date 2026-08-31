package com.gdufe.leetcode.lesson5;

import java.util.Map;
import java.util.HashMap;
import java.util.Deque;
import java.util.ArrayDeque;

public class LeetCode_20 {
    // LeetCode 20 有效的括号
    public boolean isValid(String s) {
        Map<Character, Character> match = Map.of(')', '(', ']', '[', '}', '{');

        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (!match.containsKey(c)) {
                stack.push(c);
            } else if (stack.isEmpty() || stack.peek() != match.get(c)) {
                return false;
            } else {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String args[]) {
        LeetCode_20 leetCode_20 = new LeetCode_20();
        String s = "()[]{}";
        System.out.println(leetCode_20.isValid(s));
    }
}