package be.vdab.personeel.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target({ TYPE, FIELD, METHOD, ANNOTATION_TYPE })
@Constraint(validatedBy = SocialSecurityNumberConstraintValidator.class)
public @interface SocialSecurityNumberConstraint {
	
	String message() default
	"{be.vdab.personeel.constraints.SocialSecurityNumberConstraint.message}";
	
	Class<?>[] groups()
	default {};
	
	Class<? extends Payload>[] payload()
	default {};
}
