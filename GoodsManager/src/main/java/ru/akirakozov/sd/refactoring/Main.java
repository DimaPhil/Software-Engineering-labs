package ru.akirakozov.sd.refactoring;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.akirakozov.sd.refactoring.servlet.AddProductServlet;
import ru.akirakozov.sd.refactoring.servlet.GetProductsServlet;
import ru.akirakozov.sd.refactoring.servlet.QueryServlet;
import ru.akirakozov.sd.refactoring.sql.DAO;
import ru.akirakozov.sd.refactoring.sql.SqlManager;

/**
 * @author akirakozov
 */
public class Main {
    public static void main(String[] args) throws Exception {
        SqlManager manager = new SqlManager();
        manager.executeUpdate("DROP TABLE IF EXISTS PRODUCT");
        manager.executeUpdate("CREATE TABLE IF NOT EXISTS PRODUCT" +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " NAME           TEXT    NOT NULL, " +
                " PRICE          INT     NOT NULL)");
        manager.finish();

        Server server = new Server(8081);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        DAO dao = new DAO();
        context.addServlet(new ServletHolder(new AddProductServlet(dao)), "/add-product");
        context.addServlet(new ServletHolder(new GetProductsServlet(dao)),"/get-products");
        context.addServlet(new ServletHolder(new QueryServlet(dao)),"/query");

        server.start();
        server.join();
    }
}
