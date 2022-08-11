package programmers.dfs.아이템줍기;

import java.util.*;

public class FailSolution {
    static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        List<Node> vertexList= new ArrayList<>();
        List<Node> edgeList= new ArrayList<>();
        List<Node> bannedNode = new ArrayList<>();
        Queue<Node> tempQueue = new LinkedList<>();
        Queue<Node> bfsQ = new LinkedList<>();

        for (int[] r : rectangle){
             tempQueue.add(new Node(r[0], r[1]));
             tempQueue.add(new Node(r[0], r[3]));
             tempQueue.add(new Node(r[2], r[3]));
             tempQueue.add(new Node(r[2], r[1]));
             while(!tempQueue.isEmpty()){
                 Node n = tempQueue.poll();
                 if (bannedNode.contains(n)) continue;
                 vertexList.add(n);
             }
             for (int i = r[0]; i <= r[2]; i++){
                 for (int j = r[1]; j <= r[3]; j++){
                     if (i == r[0] || i == r[2] || j == r[1] || j == r[3]){
                         Node crossNode = new Node(i,j);
                         if (edgeList.contains(crossNode)) vertexList.add(crossNode);
                         else edgeList.add(new Node(i,j));

                     } else{
                         bannedNode.add(new Node(i,j));
                     }
                 }
             }
        }

        for (Node n : bannedNode){
            if (vertexList.contains(n)) vertexList.remove(n);
        }

        vertexList.add(new Node(itemX, itemY));

        vertexList.forEach(o -> {
            if (o.x == characterX || o.y == characterY) bfsQ.add(o);
        });

        while(!bfsQ.isEmpty()){
            Node polled = bfsQ.poll();
            Map<Integer, Node> candidate = new TreeMap<>();
            vertexList.forEach(o -> {
                if (o.x == polled.x || o.y == polled.y) {
                    candidate.put(o.diff(polled), o);
                    if (o.distance == 0) o.setDistance(o.diff(polled) + polled.distance);
                    else Math.min(o.distance,  o.diff(polled)+ polled.distance);
                }
            });
            bfsQ.add(candidate.get(candidate.keySet().stream().findFirst()));
        }


        System.out.println(vertexList);
        return vertexList.get(vertexList.indexOf(new Node(itemX, itemY))).distance;
    }

    static class Node{
        int x;
        int y;
        int distance = 0;

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int diff(Node node){
            if (x == node.x){
                return Math.abs(this.y - node.y);
            } else if (y == node.y){
                return Math.abs(this.x - node.x);
            } else {
                return -1;
            }
        }

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static void main(String[] args) {
        int[][] rec = new int[][]{{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}};
//        int[][] rec = new int[][]{{1,1,8,4}, {2,2,4,9}, {3,6,9,8}, {6,3,7,7}};
        solution(rec, 1,3,7,8);

    }
}
