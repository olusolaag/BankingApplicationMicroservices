package com.learning.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Before("execution(* com.learning.controller.*.*(..))")
	// Activates for all classes in controller, and all methods in that class, with n(..) arguments in those classes.
	public void logRestController() {
		//LOGGER.info("before advice called");
	}
	
	@After("execution(* com.learning.controller.CustomerController.*(..))")
	public void logRestController2() {
		//LOGGER.info("After advice called");
	}
	
	@Pointcut("within(@org.springframework.stereotype.Repository *)"+
	"||within(@org.springframework.stereotype.Service *)" +
	"||within(@org.springframework.web.bind.annotation.RestController *)")
	public void springBeanPointCutExp() {
		
	}
	
	@Pointcut("within(com.learning.controller..*)"+"|| within(com.learning.service..*)")
	public void springPointCutExp2() {
		
	}

	@AfterThrowing(pointcut = "springBeanPointCutExp() && springPointCutExp2()", throwing = "e")
	public void logAfterThrowingException(JoinPoint joinPoint, Throwable e) {
		System.out.println(joinPoint);
		System.out.println(e);
	}
}
