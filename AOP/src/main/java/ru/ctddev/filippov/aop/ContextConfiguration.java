package ru.ctddev.filippov.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.ctddev.filippov.aop.dao.CustomerInMemoryDao;
import ru.ctddev.filippov.aop.aspect.LoggingExecutionTimeAspect;
import ru.ctddev.filippov.aop.domain.CustomerManager;
import ru.ctddev.filippov.aop.domain.CustomerManagerImpl;

@Configuration
@EnableAspectJAutoProxy
class ContextConfiguration {
    @Bean
    public CustomerManager customerManager() {
        return new CustomerManagerImpl(new CustomerInMemoryDao());
    }

    @Bean
    public LoggingExecutionTimeAspect aspect() {
        return new LoggingExecutionTimeAspect();
    }
}
