package com.dongbin.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        System.out.println((String)map.get("dd"));
    }


    public static int[] twoSum(int[] nums, int target) {

        int[] result = new int[2];

        Map<Integer, Integer> map = new HashMap<>();

        for (int index = 0; index < nums.length; index++) {
            //if (nums[index] >= target) continue;
            if (map.get(target - nums[index]) != null) {
                result[0] = index;
                result[1] = map.get(target - nums[index]);
                break;
            }

            map.put(nums[index], index);
        }

        return result;
    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int mod = l1.val + l2.val;
        ListNode result = new ListNode(mod % 10);
        ListNode current = result;
        mod = mod / 10;
        while (l1.next != null || l2.next != null) {
            if (l1.next != null && l2.next == null) {
                mod = l1.next.val + mod;
                l1 = l1.next;
            } else if (l1.next == null && l2.next != null) {
                mod = l2.next.val + mod;
                l2 = l2.next;
            } else {
                mod = l1.next.val + l2.next.val + mod;
                l1 = l1.next;
                l2 = l2.next;
            }
            ListNode sub = new ListNode(mod % 10);
            current.next = sub;
            mod = mod / 10;
            current = sub;
        }
        if (mod > 0) {
            current.next = new ListNode(mod);
        }
        return result;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        int mid = length / 2;

        int index = 0, index1 = 0, index2 = 0;
        int a = 0, b = 0, current = 0;
        while (true) {
            index++;
            if (index1 < nums1.length && index2 < nums2.length) {
                if (nums1[index1] > nums2[index2]) {
                    current = nums2[index2];
                    index2++;
                } else {
                    current = nums1[index1];
                    index1++;
                }
            }

            if (index1 < nums1.length) {
                current = nums1[index1];
                index1++;
            }

            if (index2 < nums2.length) {
                current = nums2[index2];
                index2++;
            }

            if (index == mid) {
                a = current;
            }

            if (index == mid + 1) {
                b = current;
                break;
            }
        }

        if (length % 2 == 0) {
            return (a + b) / 2.0;
        } else {
            return b;
        }
    }


    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        List<String> resultList = new ArrayList();
        for (int index = 0; index < numRows; index++) {
            resultList.add("");
        }
        char[] chars = s.toCharArray();
        int current = 0;
        boolean add = true;
        for (int index = 0; index < chars.length; index++) {
            resultList.set(current, resultList.get(current) + chars[index]);

            if (add) {
                current++;
                if (current == numRows - 1) {
                    add = false;
                }
            } else {
                current--;
                if (current == 0) {
                    add = true;
                }
            }
        }

        String result = "";
        for (int index = 0; index < resultList.size(); index++) {
            result += resultList.get(index);
        }

        return result;
    }
}
