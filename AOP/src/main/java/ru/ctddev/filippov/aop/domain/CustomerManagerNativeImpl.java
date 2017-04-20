package ru.ctddev.filippov.aop.domain;

import ru.ctddev.filippov.aop.dao.CustomerInMemoryDao;

public class CustomerManagerNativeImpl {
    private CustomerInMemoryDao customerDao = new CustomerInMemoryDao();

    public CustomerManagerNativeImpl(CustomerInMemoryDao customerDao) {
        this.customerDao = customerDao;
    }

    public int addCustomer(Customer customer) {
        long startNs = System.nanoTime();
        System.out.println("Start adding user");

        int id = customerDao.addCustomer(customer);
        System.out.println("Finish adding user, execution time in ns: "
                + (System.nanoTime() - startNs));

        return id;
    }

    public Customer findCustomer(int id) {
        long startNs = System.nanoTime();
        System.out.println("Start finding user");

        Customer customer = customerDao.findCustomer(id);
        System.out.println("Finish finding user, execution time in ns: "
                + (System.nanoTime() - startNs));

        return customer;
    }

}
