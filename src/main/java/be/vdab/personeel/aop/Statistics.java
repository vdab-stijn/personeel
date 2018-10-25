package be.vdab.personeel.aop;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Statistics {

	private static final Logger LOGGER
	= LoggerFactory.getLogger(Statistics.class);
	private final ConcurrentHashMap<String, AtomicInteger> statistics
	= new ConcurrentHashMap<>();
	
	@After("be.vdab.personeel.aop.PointcutExpressions.services()")
	public void updateStatistic(final JoinPoint joinPoint) {
		final String joinPointSignature
		= joinPoint.getSignature().toLongString();
		
		final AtomicInteger previousCallCount
		= statistics.putIfAbsent(joinPointSignature, new AtomicInteger(1));
		
		int callCount = previousCallCount == null ? 1 :
			previousCallCount.incrementAndGet();
		
		LOGGER.info("{} was called {} time(s)", joinPointSignature, callCount);
	}
}
