import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dmitry on 16.12.16.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        List<String> arguments = new ArrayList<>(Arrays.asList(args));
        DrawingAPI api;
        if (arguments.contains("javafx")) {
            api = new JavaFxDrawingAPI(600, 400);
        } else {
            api = new AWTDrawingAPI(600, 400);
        }
        Graph graph;
        if (arguments.contains("matrix")) {
            graph = new MatrixGraph(api);
        } else {
            graph = new EdgeListGraph(api);
        }
        graph.readGraph();
        graph.drawGraph();
    }
}
