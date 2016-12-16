import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 16.12.16.
 */
class EdgeListGraph extends Graph {
    private List<Integer>[] graph;
    private static final int R = 100;
    private static final int radius = 50;
    private int CENTER_X;
    private int CENTER_Y;

    EdgeListGraph(DrawingAPI drawingAPI) {
        super(drawingAPI);
        CENTER_X = drawingAPI.getDrawingAreaWidth() / 2;
        CENTER_Y = drawingAPI.getDrawingAreaHeight() / 2;
    }

    @SuppressWarnings("unchecked")
    public void readGraph() throws IOException {
        System.out.println("Enter number of vertices and edges:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = reader.readLine().split(" ");
        if (parts.length != 2) {
            throw new RuntimeException("Expected two numbers");
        }
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        System.out.println("Enter list of edges:");
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            parts = reader.readLine().split(" ");
            if (parts.length != 2) {
                throw new RuntimeException("Expected two numbers");
            }
            int v = Integer.parseInt(parts[0]) - 1;
            int u = Integer.parseInt(parts[1]) - 1;
            graph[v].add(u);
            graph[u].add(v);
        }
    }

    private void drawEdge(int x1, int y1, int x2, int y2) {
        drawingAPI.drawLine(x1 + 20, y1 + 20, x2 + 20, y2 + 20);
    }

    @Override
    public void drawGraph() {
        int n = graph.length;
        int[] x = new int[n];
        int[] y = new int[n];
        initialPlacement(n, x, y, CENTER_X, CENTER_Y, R, radius);
        for (int v = 0; v < n; v++) {
            for (int u : graph[v]) {
                drawEdge(x[v], y[v], x[u], y[u]);
            }
        }
    }
}
