package ru.ctddev.filippov.aop.domain;

public interface CustomerManager {
    int addCustomer(Customer customer);
    Customer findCustomer(int id);
}
