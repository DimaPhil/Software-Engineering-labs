package ru.akirakozov.sd.refactoring.sql;

import ru.akirakozov.sd.refactoring.html.HtmlBuilder;
import ru.akirakozov.sd.refactoring.servlet.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Created by dmitry on 29.10.16.
 */
public class DAO {
    private SqlManager manager;

    public DAO() {
        manager = new SqlManager();
    }

    public void insertProduct(String name, long price) {
        manager.executeUpdate("INSERT INTO PRODUCT " +
                "(NAME, PRICE) VALUES (\'" + name + "\'," + price + ")");
        manager.finish();
    }

    private <T> T runQuery(String sql, Function<ResultSet, T> function) {
        try (ResultSet rs = manager.executeQuery(sql)) {
            try {
                return function.apply(rs);
            } finally {
                manager.finish();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Product> readProducts(ResultSet rs) {
        List<Product> products = new ArrayList<>();
        try {
            while (rs.next()) {
                String name = rs.getString("name");
                int price = rs.getInt("price");
                products.add(new Product(name, price));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int readInt(ResultSet rs) {
        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getAllProducts() {
        return runQuery("SELECT * FROM PRODUCT", this::readProducts);
    }

    public Product getProductWithMaxPrice() {
        return runQuery("SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1", this::readProducts).get(0);
    }

    public Product getProductWithMinPrice() {
        return runQuery("SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1", this::readProducts).get(0);
    }

    public int getProductsCount() {
        return runQuery("SELECT COUNT(*) FROM PRODUCT", this::readInt);
    }

    public int getProductsSum() {
        return runQuery("SELECT SUM(price) FROM PRODUCT", this::readInt);
    }
}
