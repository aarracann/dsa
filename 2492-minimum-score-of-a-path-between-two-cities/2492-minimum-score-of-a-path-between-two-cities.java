import java.util.*;

class Solution {
    public int minScore(int n, int[][] roads) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] road : roads) {
            graph.computeIfAbsent(road[0], k -> new ArrayList<>()).add(new int[]{road[1], road[2]});
            graph.computeIfAbsent(road[1], k -> new ArrayList<>()).add(new int[]{road[0], road[2]});
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        queue.offer(1);
        visited[1] = true;
        int minScore = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            if (!graph.containsKey(node)) continue;
            for (int[] neighborEdge : graph.get(node)) {
                int neighbor = neighborEdge[0];
                int distance = neighborEdge[1];
                minScore = Math.min(minScore, distance);

                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        return minScore;
    }
}