package ru.ctddev.filippov.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggingAnnotatedMethodsAspect {

    @Around("@annotation(ru.ctddev.filippov.aop.aspect.Profile) && execution(* *(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startNs = System.nanoTime();
        System.out.println("Start method " + joinPoint.getSignature().getName());

        Object result = joinPoint.proceed(joinPoint.getArgs());

        System.out.println("Finish method " + joinPoint.getSignature().getName()
                + ", execution time in ns: " + (System.nanoTime() - startNs));

        return result;
    }
}
