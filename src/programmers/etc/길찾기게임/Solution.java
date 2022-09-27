package programmers.etc.길찾기게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static List<Node> nodeList = new ArrayList<>();
    static List<Integer> preOrderList = new ArrayList<>();
    static List<Integer> postOrderList = new ArrayList<>();

    static int[][] solution(int[][] nodeinfo) {
        for (int i = 0; i < nodeinfo.length; i++) nodeList.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
        nodeList.sort((o1, o2) -> o1.locY != o2.locY ? o2.locY - o1.locY : o1.locX - o2.locX);

        Node root = nodeList.get(0);

        findChild(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        getOrder(root);
        int[] preOrder = preOrderList.stream().mapToInt(Integer::intValue).toArray();
        int[] postOrder = postOrderList.stream().mapToInt(Integer::intValue).toArray();
        return new int[][]{preOrder, postOrder};
    }

    private static void findChild(Node root, int min, int max) {
        for (Node n : nodeList) {
            if (root.left != null && root.right != null) break;
            if (root.locY <= n.locY) continue;
            if (root.left == null && (n.locX < root.locX && n.locX > min)) root.left = n;
            if (root.right == null && (n.locX > root.locX && n.locX < max)) root.right = n;
        }
        if (root.left != null) findChild(root.left, min, root.locX);
        if (root.right != null) findChild(root.right, root.locX, max);
    }

    static class Node {
        int val, locX, locY;
        Node left, right;

        public Node(int val, int locX, int locY) {
            this.val = val;
            this.locX = locX;
            this.locY = locY;
        }
    }

    static void getOrder(Node node) {
        if (node != null) {
            preOrderList.add(node.val);
            if (node.left != null) getOrder(node.left);
            if (node.right != null) getOrder(node.right);
            postOrderList.add(node.val);
        }
    }

    static void transBracket() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String val = reader.readLine();  // Hi Anna
        val = val.replaceAll("]", "}").replaceAll("\\[", "{");
        System.out.println(val);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(solution(new int[][]{{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}}
        ));
    }
}
