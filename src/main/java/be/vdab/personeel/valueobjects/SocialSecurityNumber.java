package be.vdab.personeel.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Transient;

import org.slf4j.LoggerFactory;

import be.vdab.personeel.entities.Employee;

//https://vladmihalcea.com/how-to-map-java-and-sql-arrays-with-jpa-and-hibernate/
//https://docs.jboss.org/hibernate/orm/5.3/javadocs/org/hibernate/type/descriptor/java/BigDecimalTypeDescriptor.html
//https://docs.spring.io/spring/docs/3.2.0.RC1/reference/html/validation.html

public class SocialSecurityNumber implements Serializable {

	/** Implements Serializable */
	private static final long serialVersionUID = -4635254872941040510L;
	
	@Transient
	private final String[] parts = new String[5];
	
	@Transient
	private static final int YEAR = 0;
	@Transient
	private static final int MONTH = 1;
	@Transient
	private static final int DAY = 2;
	@Transient
	private static final int COUNTER = 3;
	@Transient
	private static final int CONTROL = 4;
	
	protected SocialSecurityNumber() {}
	
	public SocialSecurityNumber(
			final BigDecimal sqlBigInt) {
		final String number = sqlBigInt.toString();
		
		LoggerFactory.getLogger(SocialSecurityNumber.class).error("SQL: " + sqlBigInt);
		LoggerFactory.getLogger(SocialSecurityNumber.class).error("JAVA: " + number);
		
		if (number.length() != 11)
				throw new IllegalArgumentException(
						"A social security number has 11 digits (argument: " +
								number + ")");
		
		final String year = number.substring(0, 2);
		final String month = number.substring(2, 4);
		final String day = number.substring(4, 6);
		final String counter = number.substring(6, 9);
		final String control = number.substring(9, 11);
		
		try {
			Integer.parseInt(year);
			Integer.parseInt(month);
			Integer.parseInt(day);
			Integer.parseInt(counter);
			Integer.parseInt(control);
		}
		catch (final NumberFormatException nfe) {
			throw new IllegalArgumentException(
					"A social security number consists of only digits " +
							"(argument: " + number + ")");
		}
		
		parts[YEAR] = year;
		parts[MONTH] = month;
		parts[DAY] = day;
		parts[COUNTER] = counter;
		parts[CONTROL] = control;
	}
	
	public int getBaseNumberForValidation() {
		return getBaseNumberForValidation("");
	}
	
	public int getBaseNumberForValidation(final String prefixNumber) {
		return Integer.parseInt(prefixNumber + 
				parts[YEAR] + parts[MONTH] + parts[DAY] + parts[COUNTER]);
	}
	public int getControlNumber() {
		return Integer.parseInt(parts[CONTROL]);
	}
	
	@Override
	public String toString() {
		return 	parts[YEAR] +
				parts[MONTH] +
				parts[DAY] +
				parts[COUNTER] +
				parts[CONTROL];
	}
	
	public BigDecimal toBigDecimal() {
		return new BigDecimal(toString());
	}
	
	private static final String REGEX
	= "([0-9]{2})([0-9]{2})([0-9]{2})([0-9]{3})([0-9]{2})";
	private static final int MODULO = 97;
	public final boolean validateFormat() {
		return toString().matches(REGEX);
	}
	public boolean validate(final Employee employee) {
		if (!validateFormat()) return false;
		
		final LocalDate date = employee.getBirthDate();
		
		int year = Integer.parseInt(parts[YEAR]);
		int month = Integer.parseInt(parts[MONTH]);
		int day = Integer.parseInt(parts[DAY]);
		
		if (date.getYear() < 2000)
			if (Integer.parseInt("19" + year) != date.getYear()) return false;
		
		if (date.getYear() >= 2000)
			if (Integer.parseInt("20" + year) != date.getYear()) return false;
		
		if (date.getMonthValue() != month) return false;
		if (date.getDayOfMonth() != day) return false;
		

		return getControlNumber()
				== MODULO - (getBaseNumberForValidation(
						date.getYear() >= 2000 ? "2" : "") % MODULO);
	}
	
	@Override
	public boolean equals(final Object object) {
		if (this == object) return true;
		
		if (!(object instanceof SocialSecurityNumber)) return false;
		
		final SocialSecurityNumber number = (SocialSecurityNumber)object;
		
		return	parts.equals(number.parts);
	}
	
	@Override
	public int hashCode() {
		return (int)(37 * getBaseNumberForValidation());
	}
}
