package ru.ctddev.filippov.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.ctddev.filippov.aop.aspect.LoggingExecutionTimeAspect;
import ru.ctddev.filippov.aop.domain.Customer;
import ru.ctddev.filippov.aop.domain.CustomerManager;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(ContextConfiguration.class);

        CustomerManager customerManager = ctx.getBean(CustomerManager.class);
        int id = customerManager.addCustomer(new Customer("Petr"));
        int id2 = customerManager.addCustomer(new Customer("Anton"));
        int id3 = customerManager.addCustomer(new Customer("Evgeny"));
        Customer customer = customerManager.findCustomer(id);

        System.out.println("Found customer name: " + customer.name);
        for (Map.Entry<String, Long> entry : LoggingExecutionTimeAspect.sum.entrySet()) {
            System.out.println(entry.getKey() + ":");
            System.out.println("Summary: " + entry.getValue() + ", average: " + entry.getValue() * 1.0 / LoggingExecutionTimeAspect.cnt.get(entry.getKey()));
        }
    }
}
