package ru.akirakozov.sd.refactoring.queries;

import ru.akirakozov.sd.refactoring.html.HtmlBuilder;
import ru.akirakozov.sd.refactoring.sql.DAO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dmitry on 29.10.16.
 */
public class UnknownQuery implements Query {
    @Override
    public String getType() {
        return "unknown";
    }

    public void process(DAO dao, HttpServletResponse response, String... arguments) throws IOException {
        HtmlBuilder builder = new HtmlBuilder();
        builder.addLine("Unknown command: " + arguments[0]);
        builder.dump(response);
    }
}
