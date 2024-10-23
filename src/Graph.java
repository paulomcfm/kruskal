import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class Graph {
    int V, E;
    Edge[] edges;

    public Graph(int V, int E) {
        this.V = V;
        this.E = E;
        edges = new Edge[E];
    }

    public void fillMatrix(BufferedReader br) throws IOException {
        String line;
        int edgeIndex = 0;
        br.readLine(); // Skip the header line
        int row = 0;
        while ((line = br.readLine()) != null) {
            String[] numbers = line.split(",");
            for (int col = 0; col < numbers.length; col++) {
                int weight = Integer.parseInt(numbers[col]);
                if (weight != 0) {
                    boolean exists = false;
                    for (int i = 0; i < edgeIndex; i++) {
                        if ((edges[i].src == col && edges[i].dest == row) ||
                                (edges[i].src == row && edges[i].dest == col)) {
                            exists = true;
                            break;
                        }
                    }
                    if (!exists) {
                        edges[edgeIndex++] = new Edge(row, col, weight);
                    }
                }
            }
            row++;
        }
    }

    public void printMatrix() {
        for (int i = 0; i < edges.length; i++) {
            System.out.println(edges[i].src + " - " + edges[i].dest + ": " + edges[i].weight);
        }
    }

    int find(int[] parent, int i) {
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }

    void union(int[] parent, int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        parent[xset] = yset;
    }

    void kruskalMST() {
        Edge[] result = new Edge[V - 1];
        int e = 0;
        int i = 0;

        Arrays.sort(edges);

        int[] parent = new int[V];
        Arrays.fill(parent, -1);

        while (e < V - 1) {
            Edge next_edge = edges[i++];

            int x = find(parent, next_edge.src);
            int y = find(parent, next_edge.dest);

            if (x != y) {
                result[e++] = next_edge;
                union(parent, x, y);
            }
        }

        System.out.println("Edges in the MST:");
        for (i = 0; i < e; ++i)
            System.out.println(result[i].src + " - "
                    + result[i].dest + ": " + result[i].weight);
    }
}