package graphs;
import java.util.*;

public class Task1 {

    private int n;
    private int m;
    private int s;
    private List<List<Integer>> adj;
    private boolean[] visited;
    private List<Integer> postOrder;

    public Task1() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество вершин в графе: ");
        n = sc.nextInt();
        System.out.print("Введите количество ребер в графе: ");
        m = sc.nextInt();
        System.out.print("Введите интересующую вершину: ");
        s = sc.nextInt();

        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
        }

        visited = new boolean[n];
        postOrder = new ArrayList<>();
    }

    private void dfs() {

        Deque<int[]> stack = new ArrayDeque<>();

        visited[s] = true;
        stack.push(new int[]{s, 0});

        while (!stack.isEmpty()) {
            int[] top = stack.peek();
            int v = top[0];
            int idx = top[1];

            if (idx < adj.get(v).size()) {
                int next = adj.get(v).get(idx);
                top[1]++;

                if (!visited[next]) {
                    visited[next] = true;
                    stack.push(new int[]{next, 0});
                }
            } else {
                postOrder.add(v);
                stack.pop();
            }
        }
    }
    private void printResult() {
        System.out.print("Достижимые из " + s + ": ");
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        System.out.print("Порядок завершения: ");
        for (int v : postOrder) {
            System.out.print(v + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Task1 task = new Task1();
        task.dfs();
        task.printResult();
    }
}
