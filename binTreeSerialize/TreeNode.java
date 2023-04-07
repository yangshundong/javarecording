package com.company.binTreeSerialize;

public class TreeNode {
  int val = 0;
  TreeNode left = null;
  TreeNode right = null;

  public TreeNode(int val) {
    this.val = val;

  }

  public static void main(String[] args) {
    TreeNode t0 = new TreeNode(0);
    TreeNode t1 = new TreeNode(100);
    TreeNode t2 = new TreeNode(2);
    t0.left = t1;
    t0.right = t2;


    solutionv02 solu = new solutionv02();
    String s = solu.Serialize(t0);
    System.out.println(s);
    TreeNode ret = solu.Deserialize(s);
    int i=0;



  }

}