package be.vdab.personeel.web.session;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class EmployeeDataDefault implements Serializable, EmployeeData {

	/** Implements Serializable */
	private static final long serialVersionUID = -4635254872941040510L;

	private long employeeId;
	
	@Override
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public Long getEmployeeId() {
		return employeeId;
	}
}
