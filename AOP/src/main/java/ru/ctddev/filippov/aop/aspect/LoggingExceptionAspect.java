package ru.ctddev.filippov.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import ru.ctddev.filippov.aop.dao.EntityNotFoundException;

@Aspect
public class LoggingExceptionAspect {

    @AfterThrowing(
            pointcut = "execution(* ru.ctddev.filippov.aop.domain.CustomerManager.*(..))",
            throwing= "error")
    public void logAfterThrowing(JoinPoint joinPoint, EntityNotFoundException error) {
        System.out.println("logAfterThrowing() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("Exception message: " + error.getMessage());
        System.out.println("******");
    }
}
