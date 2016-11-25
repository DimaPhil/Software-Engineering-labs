package ru.akirakozov.sd.refactoring.html;

import ru.akirakozov.sd.refactoring.servlet.Product;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dmitry on 28.10.16.
 */
public class HtmlBuilder {
    private List<String> lines;
    private List<String> headers;
    private List<String> bolds;

    public HtmlBuilder() {
        lines = new ArrayList<>();
        headers = new ArrayList<>();
        bolds = new ArrayList<>();
    }

    public void addLine(String line) {
        lines.add(line);
    }

    public void addLine(List<Object> objects) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < objects.size() - 1; i++) {
            line.append(objects.get(i).toString()).append("\t");
        }
        line.append(objects.get(objects.size() - 1).toString());
        lines.add(line.toString());
    }

    public void addHeader(String header) {
        headers.add(header);
    }

    public void addBold(String bold) {
        bolds.add(bold);
    }

    public void dump(HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println("<html><body>");
        for (String header : headers) {
            writer.println("<h1>" + header + "</h1>");
        }
        for (String bold : bolds) {
            writer.println("<b>" + bold + "</b>");
        }
        for (String line : lines) {
            writer.println(line + "</br>");
        }
        writer.println("</body></html>");
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void collectHtml(HttpServletResponse response, List<Product> products) throws IOException {
        for (Product product : products) {
            addLine(Arrays.asList(product.getName(), product.getPrice()));
        }
        dump(response);
    }

    public void collectHtml(HttpServletResponse response, String line) throws IOException {
        addLine(line);
        dump(response);
    }
}