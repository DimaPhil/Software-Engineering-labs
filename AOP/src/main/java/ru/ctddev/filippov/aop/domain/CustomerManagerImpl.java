package ru.ctddev.filippov.aop.domain;

import ru.ctddev.filippov.aop.dao.CustomerInMemoryDao;
import ru.ctddev.filippov.aop.aspect.Profile;

public class CustomerManagerImpl implements CustomerManager {
    private CustomerInMemoryDao customerDao = new CustomerInMemoryDao();

    public CustomerManagerImpl(CustomerInMemoryDao customerDao) {
        this.customerDao = customerDao;
    }

    @Profile
    public int addCustomer(Customer customer) {
        try {
            System.out.println(findCustomer(0));
        } catch (Throwable ignored) {}
        return customerDao.addCustomer(customer);
    }

    @Profile
    public Customer findCustomer(int id) {
        return customerDao.findCustomer(id);
    }
}
