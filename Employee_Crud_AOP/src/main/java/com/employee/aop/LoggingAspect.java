package com.employee.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Before("execution(* com.employee.service.*.*(..))")
	public void logMethodEntry() {
		logger.info("Entering method...");
	}

	@AfterReturning(pointcut = "execution(* com.employee.service.*.*(..))", returning = "result")
	public void logMethodExit(Object result) {
		logger.info("Exiting method with result: {}", result);
	}

	@AfterThrowing(pointcut = "execution(* com.employee.service.*.*(..))", throwing = "exception")
	public void logMethodException(Exception exception) {
		logger.error("Exception in method: {}", exception.getMessage());
	}

}