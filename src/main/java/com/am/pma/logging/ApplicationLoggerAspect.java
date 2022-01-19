package com.am.pma.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class ApplicationLoggerAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    // empty method just to define the locations (packages) that will trigger logging
    @Pointcut("within(com.am.pma.controllers..*)")
    public void definePackagePointcuts() { }

    @Around("definePackagePointcuts()")
    public Object logAround(ProceedingJoinPoint jp) {
        log.debug("\n\n\n");
        log.debug("*********** Before Method Execution ************ \n {}.{} () with argument[s] = {}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName(),
                Arrays.toString(jp.getArgs())
                );
        log.debug("************************************************\n\n\n");

        Object result = null;
        try {
            result = jp.proceed();
        } catch(Throwable error) {
            error.printStackTrace();
        }

        log.debug("\n\n\n");
        log.debug("*********** After Method Execution ************ \n {}.{} () with argument[s] = {}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName(),
                Arrays.toString(jp.getArgs())
        );
        log.debug("************************************************\n\n\n");

        return result;
    }
}
