package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt(); // Количество вершин (дисциплин)
        int m = scanner.nextInt(); // Количество ребер (зависимостей)

        // Инициализация списка смежности и массива степеней захода (in-degrees)
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        int[] inDegree = new int[n];

        // Считывание графа
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            adjacencyList.get(u).add(v);
            inDegree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<List<Integer>> layers = new ArrayList<>();
        int processedVerticesCount = 0;

        while (!queue.isEmpty()) {
            int currentLayerSize = queue.size();
            List<Integer> currentLayer = new ArrayList<>();

            for (int i = 0; i < currentLayerSize; i++) {
                int currentVertex = queue.poll();
                currentLayer.add(currentVertex);
                processedVerticesCount++;

                for (int neighbor : adjacencyList.get(currentVertex)) {
                    inDegree[neighbor]--;
                    if (inDegree[neighbor] == 0) {
                        queue.add(neighbor);
                    }
                }
            }
            layers.add(currentLayer);
        }

        if (processedVerticesCount < n) {
            System.out.println("CYCLE");
        } else {
            StringBuilder result = new StringBuilder("layers: ");
            for (int i = 0; i < layers.size(); i++) {
                List<Integer> layer = layers.get(i);
                for (int j = 0; j < layer.size(); j++) {
                    result.append(layer.get(j));
                    if (j < layer.size() - 1) {
                        result.append(" ");
                    }
                }
                if (i < layers.size() - 1) {
                    result.append(" | ");
                }
            }
            System.out.println(result.toString());
        }
    }
}
