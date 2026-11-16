package graphs;

import java.util.Arrays;
import java.util.Scanner;

public class Task3 {

    // Массивы для DSU
    static int[] parent;
    static int[] rank;

    static int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }

    static boolean union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);

        if (rootI != rootJ) {
            if (rank[rootI] < rank[rootJ]) {
                parent[rootI] = rootJ;
            } else if (rank[rootI] > rank[rootJ]) {
                parent[rootJ] = rootI;
            } else {
                parent[rootJ] = rootI;
                rank[rootI]++;
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) return;

        int n = scanner.nextInt(); // Количество вершин
        int m = scanner.nextInt(); // Количество ребер

        // Хранение ребер в виде двумерного массива: [номер ребра][0-вершина, 1-вершина, 2-вес]
        int[][] edges = new int[m][3];
        for (int i = 0; i < m; i++) {
            edges[i][0] = scanner.nextInt();
            edges[i][1] = scanner.nextInt();
            edges[i][2] = scanner.nextInt();
        }

        // ШАГ 1: Сортируем все ребра по возрастанию веса
        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

        // Инициализация DSU.
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int maxWeight = 0;
        int edgesCount = 0;

        // ШАГ 3: Жадно добавляем ребра
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];

            // Если ребро соединяет две разные компоненты (не создает цикл)
            if (union(u, v)) {
                maxWeight = w; // Так как массив отсортирован, каждое следующее валидное ребро тяжелее предыдущего
                edgesCount++;

                // Оптимизация: в остовном дереве ровно N-1 ребро.
                if (edgesCount == n - 1) {
                    break;
                }
            }
        }
        System.out.println("maxSelected=" + maxWeight);
    }
}