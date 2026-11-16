package graphs;

import java.util.*;

public class Task2 {

    // Класс для представления ребра графа
    static class Edge {
        int to;      // конечная вершина
        int weight;  // вес ребра

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Node implements Comparable<Node> {
        int vertex;    // вершина
        int distance;  // расстояние до неё

        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    static int[] dijkstra(List<List<Edge>> graph, int start, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // Приоритетная очередь (минимальная куча)
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;
            int d = current.distance;

            if (d > dist[u]) continue;

            for (Edge edge : graph.get(u)) {
                int v = edge.to;
                int weight = edge.weight;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new Node(v, dist[v]));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();  // количество вершин
        int m = scanner.nextInt();  // количество рёбер
        int s = scanner.nextInt();  // старт
        int t = scanner.nextInt();  // финиш
        int x = scanner.nextInt();  // промежуточная вершина

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();

            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w)); // если граф неориентированный
        }

        // Дейкстра от s
        int[] distFromS = dijkstra(graph, s, n);

        // Дейкстра от x
        int[] distFromX = dijkstra(graph, x, n);

        if (distFromS[x] == Integer.MAX_VALUE || distFromX[t] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            int totalCost = distFromS[x] + distFromX[t];
            System.out.println("cost=" + totalCost);
        }

        scanner.close();
    }
}