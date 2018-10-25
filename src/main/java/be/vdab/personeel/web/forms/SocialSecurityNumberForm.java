package be.vdab.personeel.web.forms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import be.vdab.personeel.constraints.SocialSecurityNumberConstraint;
import be.vdab.personeel.constraints.SocialSecurityNumberFormConstraint;
import be.vdab.personeel.entities.Employee;
import be.vdab.personeel.valueobjects.SocialSecurityNumber;

@SocialSecurityNumberFormConstraint
public class SocialSecurityNumberForm {

	@NotNull
	private Employee employee;
	
	@SocialSecurityNumberConstraint
	private SocialSecurityNumber socialSecurityNumber;
	
	public void setSocialSecurityNumber(
			@Valid final Employee employee,
			final SocialSecurityNumber socialSecurityNumber) {
		this.employee = employee;
		this.socialSecurityNumber = socialSecurityNumber;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public SocialSecurityNumber getSocialSecurityNumber() {
		return socialSecurityNumber;
	}
}
