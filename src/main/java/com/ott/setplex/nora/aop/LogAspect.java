package com.ott.setplex.nora.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Aspect
@Component
public class LogAspect {

	private static final Logger LOGGER = LogManager.getLogger(LogAspect.class);

	@Pointcut("execution(* com.ott.setplex.nora.controller..*(..)))")
	public void customPointcut() {

	}

	@Around("customPointcut()")
	public Object customAdvice(ProceedingJoinPoint pjp) throws Throwable {

		LOGGER.info("Before Advice");

		Object obj = pjp.proceed();

		LOGGER.info("After Advice");
		return obj;
	}

}
