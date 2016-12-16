import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by dmitry on 16.12.16.
 */
public class JavaFxDrawingAPI extends Application implements DrawingAPI {
    private Group root;
    private Canvas canvas;
    private GraphicsContext context;

    JavaFxDrawingAPI() {
    }

    @Override
    public int getDrawingAreaWidth() {
        return (int) canvas.getWidth();
    }

    @Override
    public int getDrawingAreaHeight() {
        return (int) canvas.getHeight();
    }

    @Override
    public void drawCircle(int x, int y, int r) {
        context = canvas.getGraphicsContext2D();
        context.setFill(Color.GREEN);
        context.fillOval(x, y, r, r);
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        context = canvas.getGraphicsContext2D();
        context.setFill(Color.GREEN);
        context.moveTo(x1, y1);
        context.lineTo(x2, y2);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        canvas = new Canvas(600, 400);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
