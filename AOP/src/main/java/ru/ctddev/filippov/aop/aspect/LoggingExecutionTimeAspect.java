package ru.ctddev.filippov.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class LoggingExecutionTimeAspect {
    public static Map<String, Long> sum = new HashMap<>();
    public static Map<String, Long> cnt = new HashMap<>();

    @Around("execution(* ru.ctddev.filippov.aop.domain.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startNs = System.nanoTime();
        System.out.println("Start method " + joinPoint.getSignature().getName());

        Object result = joinPoint.proceed(joinPoint.getArgs());

        long time = (System.nanoTime() - startNs);
        System.out.println("Finish method " + joinPoint.getSignature().getName()
                + ", execution time in ns: " + time);

        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        for (StackTraceElement e : trace) {
            System.out.println(e);
        }
        System.out.println();
        if (!sum.containsKey(joinPoint.toShortString())) {
            sum.put(joinPoint.toShortString(), time);
            cnt.put(joinPoint.toShortString(), 1L);
        } else {
            sum.put(joinPoint.toShortString(), sum.get(joinPoint.toShortString()) + time);
            cnt.put(joinPoint.toShortString(), cnt.get(joinPoint.toShortString()) + 1);
        }

        return result;
    }
}
