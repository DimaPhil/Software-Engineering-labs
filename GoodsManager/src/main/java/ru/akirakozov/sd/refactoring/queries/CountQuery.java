package ru.akirakozov.sd.refactoring.queries;

import ru.akirakozov.sd.refactoring.html.HtmlBuilder;
import ru.akirakozov.sd.refactoring.sql.DAO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Created by dmitry on 29.10.16.
 */
public class CountQuery implements Query {
    @Override
    public String getType() {
        return "count";
    }

    @Override
    public void process(DAO dao, HttpServletResponse response, String... arguments) throws IOException {
        int sum = dao.getProductsCount();
        HtmlBuilder builder = new HtmlBuilder();
        builder.addBold("Number of products: ");
        builder.collectHtml(response, String.valueOf(sum));
    }
}
