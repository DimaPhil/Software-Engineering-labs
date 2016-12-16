import java.io.IOException;

/**
 * Created by dmitry on 15.12.16.
 */
abstract class Graph {
    DrawingAPI drawingAPI;

    Graph(DrawingAPI drawingAPI) {
        this.drawingAPI = drawingAPI;
    }

    void initialPlacement(int n, int[] x, int[] y, int cx, int cy, int R, int radius) {
        for (int i = 0; i < n; i++) {
            double angle = (2 * Math.PI / n) * i;
            x[i] = cx + (int) (Math.cos(angle) * R);
            y[i] = cy + (int) (Math.sin(angle) * R);
            drawingAPI.drawCircle(x[i], y[i], radius);
        }
    }

    public abstract void readGraph() throws IOException;
    public abstract void drawGraph();
}
