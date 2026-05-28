package com.example.library.aspect;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.*;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class BookServiceAspect {

    @Before(
            "execution(* com.example.library.service.*.*(..))"
    )
    public void beforeMethod(
            JoinPoint joinPoint
    ) {

        log.info(
                "Before method: {} args: {}",
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs())
        );
    }

    @AfterReturning(
            pointcut =
                    "execution(* com.example.library.service.*.*(..))",
            returning = "result"
    )
    public void afterReturning(
            JoinPoint joinPoint,
            Object result
    ) {

        log.info(
                "After method: {} result: {}",
                joinPoint.getSignature().getName(),
                result
        );
    }

    @AfterThrowing(
            pointcut =
                    "execution(* com.example.library.service.*.*(..))",
            throwing = "ex"
    )
    public void afterThrowing(
            JoinPoint joinPoint,
            Exception ex
    ) {

        log.error(
                "Exception in method {} message {}",
                joinPoint.getSignature().getName(),
                ex.getMessage()
        );
    }
}