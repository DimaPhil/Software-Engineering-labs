package ru.akirakozov.sd.refactoring.queries;

import ru.akirakozov.sd.refactoring.html.HtmlBuilder;
import ru.akirakozov.sd.refactoring.servlet.Product;
import ru.akirakozov.sd.refactoring.sql.DAO;
import ru.akirakozov.sd.refactoring.sql.SqlManager;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by dmitry on 29.10.16.
 */
public class MinQuery implements Query {
    @Override
    public String getType() {
        return "min";
    }

    @Override
    public void process(DAO dao, HttpServletResponse response, String... arguments) throws IOException {
        Product product = dao.getProductWithMinPrice();
        HtmlBuilder builder = new HtmlBuilder();
        builder.addHeader("Products with min price: ");
        builder.collectHtml(response, Collections.singletonList(product));
    }
}
