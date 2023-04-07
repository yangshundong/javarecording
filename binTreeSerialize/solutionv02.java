package com.company.binTreeSerialize; /* public class TreeNode { int val = 0; TreeNode left = null; TreeNode right = null; public TreeNode(int val) { this.val = val; } } */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 把string换成linkedlist
 *
 * */
public class solutionv02 {
    String Serialize(TreeNode root) {
        List<Integer> strmid = new ArrayList<>();
        List<Integer> strtail = new ArrayList<>();
        strmid = midwalk(root, strmid);
        strtail = tailwalk(root, strtail);
        StringBuilder s = new StringBuilder();
        for (Integer integer : strmid) s.append(integer).append(',');
        for (Integer integer : strtail) s.append(integer).append(',');
        return s.toString();
    }

    private List tailwalk(TreeNode root, List<Integer> s) {
        if (root == null) return s;
        s = midwalk(root.left, s);
        s = midwalk(root.right, s);
        s.add(root.val);
        return s;
    }

    private List midwalk(TreeNode root, List<Integer> s) {
        if (root == null) return s;
        s = midwalk(root.left, s);
        s.add(root.val);
        s = midwalk(root.right, s);
        return s;
    }

    TreeNode Deserialize(String str) {
        Integer[] nums = Arrays.stream(str.split(",")).map(Integer::parseInt).toArray(Integer[]::new);
        Integer[] nums1 = Arrays.copyOfRange(nums, 0, nums.length / 2);
        Integer[] nums2 = Arrays.copyOfRange(nums, nums.length / 2, nums.length);
        TreeNode root = dse(nums1, nums2);
        return root;
    }

    private TreeNode dse(Integer[] smid, Integer[] stail) {
        int len = stail.length;
        if (len == 0) return null;
        Integer num = stail[len - 1];
        TreeNode root = new TreeNode(num);
        int index = 0;
        for (int i = 0; i < len; i++)
            if (smid[i] == num) {
                index = i;
                break;
            }
        Integer[] leftmid = Arrays.copyOfRange(smid, 0, index);
        Integer[] lefttail = Arrays.copyOfRange(smid, 0, index);
        Integer[] righttmid = Arrays.copyOfRange(smid, index + 1, len);
        Integer[] righttail = Arrays.copyOfRange(smid, index, len - 1);
        root.left = dse(leftmid, lefttail);
        root.right = dse(righttmid, righttail);
        return root;
    }
}