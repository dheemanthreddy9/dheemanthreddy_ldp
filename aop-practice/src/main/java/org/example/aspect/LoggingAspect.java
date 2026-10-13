package org.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {


    @Pointcut("execution(* org.example.service.PaymentService.*(..))")
    public void paymentServicePointcut() {}

    @Before("paymentServicePointcut()")
    public void logBefore(JoinPoint joinPoint) {
        // JOIN POINT: Information about the currently executed method
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("[@Before Advice] -> Entering method: " + methodName + " | Arguments: " + Arrays.toString(args));
    }

    @After("paymentServicePointcut()")
    public void logAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[@After Advice]  -> Finished method: " + methodName);
    }

    @Around("paymentServicePointcut()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        System.out.println("\n>>> [@Around Advice START] Wraps execution of: " + methodName);

        long startMs = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        long executionTime = System.currentTimeMillis() - startMs;
        System.out.println("[@Around Advice END]   Method: " + methodName + " executed in " + executionTime + " ms | Returned: " + result + " <<<\n");

        return result;
    }
}
