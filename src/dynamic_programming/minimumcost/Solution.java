package dynamic_programming.minimumcost;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Solution {
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        List<Path> paths = new ArrayList<>();
        for (int[] edge : edges) {
            Edge e = new Edge(edge[0], edge[1], edge[2]);
            final boolean[] addToLast = new boolean[1];
            final boolean[] addToFirst = new boolean[1];
            Path existingPath = paths.stream()
                    .filter(Objects::nonNull)
                    .peek(s -> { if (s.lastEl == e.x) {
                            addToLast[0] = true;
                        } else if (s.firstEl == e.y) {
                            addToFirst[0] = true;
                        } return; })
                    .filter(p -> addToLast[0] || addToFirst[0])
                    .findFirst().orElse(null);
            if (existingPath != null && addToLast[0]) {//assuming one and only path existingPath matching
                existingPath.cost += passingFees[edge[1]];
                existingPath.edges.add(e);
                existingPath.time += edge[2];
                existingPath.lastEl = edge[1];
            } else if (existingPath != null && addToFirst[0]) {//assuming one and only path existingPath matching
                existingPath.cost += passingFees[edge[0]];
                existingPath.edges.add(e);
                existingPath.time += edge[2];
                existingPath.firstEl = edge[0];
            } else { //no paths exist to join the new edge x data point, create new path
                Path path = new Path(e, passingFees[edge[0]] + passingFees[edge[1]]);
                path.edges.add(e);
                path.time = edge[2];
                paths.add(path);
            }
        }
        int minCost = paths.stream()
                .filter(path -> path.lastEl == (passingFees.length) - 1)
                .filter(path -> path.time <= maxTime)
                .map(p -> p.cost)
                .min((o1, o2) -> o1 - o2)
                .orElse(-1);

// iterate over each path , filter every path having last ele as destination, time less than max time
//Out of those return cost having least val, else return -1
        return minCost;
    }

    static class Edge {
        int x;
        int y;
        int time;

        Edge(int f, int l, int t) {
            x = f;
            y = l;
            time = t;
        }
    }

    static class Path {
        int firstEl;
        int lastEl;
        List<Edge> edges = new ArrayList<>();
        int time;
        int cost;

        Path(Edge e, int c) {
            firstEl = e.x;
            lastEl = e.y;
            cost += c;
        }
    }
}

class Main {
    public static void main(String[] args) {
        int maxTime = 25;
        int[][] edges = {{0, 1, 10}, {1, 2, 10}, {2, 5, 10}, {0, 3, 1}, {3, 4, 10}, {4, 5, 15}};
        int[] passingFees = {5, 1, 2, 20, 20, 3};

       /* int maxTime = 124;
        int[][] edges = {{1, 2, 1}, {0, 1, 123}};
        int[] passingFees = {2, 5, 4};*/

        Solution s = new Solution();
        System.out.println(s.minCost(maxTime, edges, passingFees));
    }
}
