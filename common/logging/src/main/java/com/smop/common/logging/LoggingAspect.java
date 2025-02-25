package com.smop.common.logging;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(com.smop..*)")
    public void applicationPackagePointcut() {
    }

    @Before("applicationPackagePointcut()")
    public void logBefore() {
        logger.info("Method execution started");
    }
}
