package be.vdab.personeel.aop;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logging {

	private static final Logger LOGGER
	= LoggerFactory.getLogger(Logging.class);
	
	@AfterThrowing(
			pointcut = "be.vdab.personeel.aop.PointcutExpressions.services()",
			throwing = "exception")
	public void writeException(
			final JoinPoint joinPoint, final Throwable exception) {
		final StringBuilder builder = new StringBuilder("TIME\t")
				.append(LocalDateTime.now());
		
		final Authentication authentication
		= SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null)
			builder.append("\nUser\t").append(authentication.getName());
		
		builder.append("\nMethod\t\t")
			.append(joinPoint.getSignature().toLongString());
		
		Arrays.stream(joinPoint.getArgs())
			.forEach(object -> builder.append("\nParameter\t").append(object));
		
		LOGGER.error(builder.toString(), exception);
	}
}