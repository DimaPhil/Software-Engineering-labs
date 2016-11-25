package ru.akirakozov.sd.refactoring.queries;

import ru.akirakozov.sd.refactoring.html.HtmlBuilder;
import ru.akirakozov.sd.refactoring.sql.DAO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dmitry on 29.10.16.
 */
public interface Query {
    String getType();
    void process(DAO dao, HttpServletResponse response, String... arguments) throws IOException;
}
