package be.vdab.personeel.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import be.vdab.personeel.valueobjects.SocialSecurityNumber;

public class SocialSecurityNumberConstraintValidator implements
ConstraintValidator<SocialSecurityNumberConstraint, SocialSecurityNumber>{

	private static final String REGEX
	= "([0-9]{2})([0-9]{2})([0-9]{2})([0-9]{3})([0-9]{2})";
	
	private static final int MODULO = 97;
	
	@Override
	public void initialize(final SocialSecurityNumberConstraint number) {}
	
	@Override
	public boolean isValid(
			final SocialSecurityNumber socialSecurityNumber,
			final ConstraintValidatorContext context) {
		if (socialSecurityNumber == null) return true;
		
		if (socialSecurityNumber.toString().matches(REGEX))
			return socialSecurityNumber.getControlNumber() ==
					MODULO - (socialSecurityNumber.getBaseNumber() % MODULO);
		
		return false;
	}

}
