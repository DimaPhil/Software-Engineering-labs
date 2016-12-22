import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by dmitry on 15.12.16.
 */
class AWTDrawingAPI extends JFrame implements DrawingAPI {
    private class Circle {
        int x;
        int y;
        int r;

        Circle(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }

        void draw(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.green);
            g2.fill(new Ellipse2D.Float(x, y, r, r));
        }
    }

    private class Line {
        int x1;
        int y1;
        int x2;
        int y2;

        Line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        void draw(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.green);
            g2.draw(new Line2D.Float(x1, y1, x2, y2));
        }
    }

    private List<Circle> circles;
    private List<Line> lines;

    AWTDrawingAPI(int width, int height) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, height);
        setVisible(false);
        circles = new ArrayList<>();
        lines = new ArrayList<>();
    }

    public int getDrawingAreaWidth() {
        return getBounds().width;
    }

    public int getDrawingAreaHeight() {
        return getBounds().height;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Circle c : circles) {
            c.draw(g);
        }
        for (Line l : lines) {
            l.draw(g);
        }
    }

    @Override
    public void drawCircle(int x, int y, int r) {
        setVisible(true);
        circles.add(new Circle(x, y, r));
        repaint();
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        setVisible(true);
        lines.add(new Line(x1, y1, x2, y2));
        repaint();
    }

    @Override
    public void finishDrawing() {
    }
}
