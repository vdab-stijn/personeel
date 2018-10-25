package be.vdab.personeel.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import be.vdab.personeel.valueobjects.SocialSecurityNumber;

public class SocialSecurityNumberConstraintValidator implements
ConstraintValidator<SocialSecurityNumberConstraint, SocialSecurityNumber>{
	
	@Override
	public void initialize(final SocialSecurityNumberConstraint number) {}
	
	@Override
	public boolean isValid(
			final SocialSecurityNumber socialSecurityNumber,
			final ConstraintValidatorContext context) {
		if (socialSecurityNumber == null) return true;
		
		return socialSecurityNumber.validateFormat();
	}

}
