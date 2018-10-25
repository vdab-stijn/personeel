package be.vdab.personeel.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutExpressions {

	@Pointcut("execution(* be.vdab.personeel.services.*.*(..))")
	public void services() {}
	
	@Pointcut("execution(* be.vdab.personeel.web.*.*(..))")
	public void web() {}
}