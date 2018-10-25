package be.vdab.personeel.aop;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Performance {

	private static final Logger LOGGER
	= LoggerFactory.getLogger(Performance.class);
	
	@Around("be.vdab.personeel.aop.PointcutExpressions.services()")
	public Object writePerformance(final ProceedingJoinPoint joinPoint)
	throws Throwable {
		final long before = System.nanoTime();
		
		try {
			return joinPoint.proceed();
		}
		finally {
			final long duration = System.nanoTime() - before;
			
			LOGGER.info("{} took {} nanoseconds ({} milliseconds)",
					joinPoint.getSignature().toLongString(),
					duration,
					BigDecimal.valueOf(duration / 1000000D)
						.setScale(2, RoundingMode.HALF_UP));
		}
	}
}