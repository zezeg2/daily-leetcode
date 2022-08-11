package leetcode.d220811.q98;

import javax.swing.tree.TreeNode;

class Solution {
    static boolean isValidBST(TreeNode root) {
        return recurse(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    static boolean recurse(TreeNode root, long min, long max) {
        if (root.left == null && root.right == null) {
            return min < root.val && root.val < max;
        } else if (root.left == null) {
            if (root.val < root.right.val && root.right.val < max) return recurse(root.right, root.val, max);
            else return false;
        } else if (root.right == null) {
            if (root.val > root.left.val && root.left.val > min) return recurse(root.left, min, root.val);
            else return false;
        } else {
            if (root.val >= root.right.val || root.val <= root.left.val) return false;
            return recurse(root.left, min, root.val) && recurse(root.right, root.val, max);
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }


    public static void main(String[] args) {
//        TreeNode treeNode = new TreeNode(2, new TreeNode(1, null, null), new TreeNode(3, null, null));
//        TreeNode treeNode = new TreeNode(5,new TreeNode(1,null,null),new TreeNode(4,new TreeNode(3,null,null),new TreeNode(6,null,null)));
//        TreeNode treeNode = new TreeNode(5, new TreeNode(4), new TreeNode(6, new TreeNode(3), new TreeNode(7)));
        TreeNode treeNode = new TreeNode(2147483647);

        System.out.println(isValidBST(treeNode));
    }
}

