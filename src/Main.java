import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "dados.txt";
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        int v = getNumberOfVertices(br);
        int e = getNumberOfEdges(br);
        br.close();

        Graph graph = new Graph(v,e);

        br = new BufferedReader(new FileReader(filePath));
        graph.fillMatrix(br);
        graph.printMatrix();

        graph.kruskalMST();
    }

    public static int getNumberOfEdges(BufferedReader br) throws IOException {
        int edgeCount = 0;

        String line;

        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            for (String value : values) {
                if (!value.equals("0")) {
                    edgeCount++;
                }
            }
        }

        return edgeCount / 2;
    }

    public static int getNumberOfVertices(BufferedReader br) throws IOException {
        int count = 0;
        String line = br.readLine();
        if (line != null) {
            String[] vertices = line.split(",");
            count = vertices.length;
        }
        return count;
    }
}