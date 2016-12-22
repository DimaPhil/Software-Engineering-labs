import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.function.Function;

/**
 * Created by dmitry on 16.12.16.
 */
public class JavaFxDrawingAPI extends Application implements DrawingAPI {
    private static int width;
    private static int height;

    private static Function<GraphicsContext, GraphicsContext> toDraw;

    JavaFxDrawingAPI(int width, int height) {
        JavaFxDrawingAPI.width = width;
        JavaFxDrawingAPI.height = height;
        toDraw = ignored -> ignored;
    }

    public JavaFxDrawingAPI() {}

    @Override
    public int getDrawingAreaWidth() {
        return width;
    }

    @Override
    public int getDrawingAreaHeight() {
        return height;
    }

    @Override
    public void drawCircle(int x, int y, int r) {
        Function<GraphicsContext, GraphicsContext> oldDraw = toDraw;
        toDraw = gc -> {
            gc = oldDraw.apply(gc);
            gc.fillOval(x, y, r, r);
            return gc;
        };
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        Function<GraphicsContext, GraphicsContext> oldDraw = toDraw;
        toDraw = gc -> {
            gc = oldDraw.apply(gc);
            gc.strokeLine(x1, y1, x2, y2);
            return gc;
        };
    }

    @Override
    public void finishDrawing() {
        launch();
        toDraw = ignored -> ignored;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        toDraw.apply(gc);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
