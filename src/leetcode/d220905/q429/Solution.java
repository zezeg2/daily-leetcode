package leetcode.d220905.q429;

import java.util.*;

public class Solution {

    static Map<Integer, List<Integer>> map = new LinkedHashMap<>();

    static List<List<Integer>> levelOrder(Node root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> answer = new ArrayList<>();

        recurse(root, 0);

        int count = 0;
        while (map.containsKey(count)){
            answer.add(map.get(count));
            count++;
        }
        map.clear();
        return answer;
    }

    static void recurse(Node node, int depth){
        if (!map.containsKey(depth)) map.put(depth, new ArrayList<>());
        map.get(depth).add(node.val);
        if (node.children == null) return;
        for (Node n : node.children) {
            if (n != null) {
//                if (!map.containsKey(depth)) map.put(depth, new ArrayList<>());
//                map.get(depth).add(n.val);
                recurse(n, depth+1);
            }
        }
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


    public static void main(String[] args) {

        Node root = new Node(1);
        root.children = new ArrayList<>();
        root.children.add(new Node(3));
        root.children.add(new Node(2));
        root.children.add(new Node(4));
        root.children.add(null);
        root.children.get(0).children = new ArrayList<>();
        root.children.get(0).children.add(new Node(5));
        root.children.get(0).children.add(new Node(6));
        root.children.get(0).children.add(null);

//        Node su = new Node(0);
//        su.children = new ArrayList<>();
//        su.children.add(root);

        levelOrder(root);



    }
}
