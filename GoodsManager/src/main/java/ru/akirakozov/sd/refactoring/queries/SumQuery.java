package ru.akirakozov.sd.refactoring.queries;

import ru.akirakozov.sd.refactoring.html.HtmlBuilder;
import ru.akirakozov.sd.refactoring.sql.DAO;
import ru.akirakozov.sd.refactoring.sql.SqlManager;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

/**
 * Created by dmitry on 29.10.16.
 */
public class SumQuery implements Query {
    @Override
    public String getType() {
        return "sum";
    }

    @Override
    public void process(DAO dao, HttpServletResponse response, String... arguments) throws IOException {
        int sum = dao.getProductsSum();
        HtmlBuilder builder = new HtmlBuilder();
        builder.addBold("Summary price: ");
        builder.collectHtml(response, String.valueOf(sum));
    }
}
