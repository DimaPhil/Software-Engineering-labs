package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.html.HtmlBuilder;
import ru.akirakozov.sd.refactoring.sql.DAO;
import ru.akirakozov.sd.refactoring.sql.SqlManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends HttpServlet {
    private DAO dao;
    private HtmlBuilder builder;

    public GetProductsServlet(DAO dao) {
        this.dao = dao;
        this.builder = new HtmlBuilder();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Product> products = dao.getAllProducts();
        builder.collectHtml(response, products);
    }
}
