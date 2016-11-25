package ru.akirakozov.sd.refactoring.servlet;

/**
 * Created by dmitry on 29.10.16.
 */
public class Product {
    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
