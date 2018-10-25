package be.vdab.personeel.web.forms;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class EmployeeRaiseForm {

	@NotNull
	@Positive
	private BigDecimal raise;
	
	public void setRaise(final BigDecimal raise) {
		this.raise = raise;
	}
	
	public BigDecimal getRaise() {
		return raise;
	}
}
