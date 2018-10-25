package be.vdab.personeel.web.forms;

import be.vdab.personeel.constraints.SocialSecurityNumberConstraint;
import be.vdab.personeel.valueobjects.SocialSecurityNumber;

public class SocialSecurityNumberForm {

	@SocialSecurityNumberConstraint
	private SocialSecurityNumber socialSecurityNumber;
	
	public void setSocialSecurityNumber(
			final SocialSecurityNumber socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}
	
	public SocialSecurityNumber getSocialSecurityNumber() {
		return socialSecurityNumber;
	}
}
