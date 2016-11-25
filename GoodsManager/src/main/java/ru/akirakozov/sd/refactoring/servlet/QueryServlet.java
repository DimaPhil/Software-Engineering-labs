package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.html.HtmlBuilder;
import ru.akirakozov.sd.refactoring.queries.*;
import ru.akirakozov.sd.refactoring.sql.DAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {
    private final static Query[] queriesClasses = {new CountQuery(), new MaxQuery(), new MinQuery(),
                                                   new SumQuery(), new UnknownQuery()};
    private DAO dao;
    private HtmlBuilder builder;

    public QueryServlet(DAO dao) {
        this.dao = dao;
        this.builder = new HtmlBuilder();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");
        for (Query query: queriesClasses) {
            if (query.getType().equals(command)) {
                query.process(dao, response, command);
            }
        }
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
