package be.vdab.personeel.valueobjects;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.BigIntTypeDescriptor;

//https://vladmihalcea.com/how-to-map-java-and-sql-arrays-with-jpa-and-hibernate/
//https://docs.jboss.org/hibernate/orm/5.3/javadocs/org/hibernate/type/descriptor/java/BigDecimalTypeDescriptor.html
//https://docs.spring.io/spring/docs/3.2.0.RC1/reference/html/validation.html
@Embeddable
public class SocialSecurityNumber
extends AbstractSingleColumnStandardBasicType<BigDecimal> {

	/** Implements Serializable */
	private static final long serialVersionUID = -4635254872941040510L;
	
	public static final String MAPPER_NAME = "social-security-number";

	@Transient
	private int birthYear;
	
	@Transient
	private int birthMonth;
	
	@Transient
	private int birthDay;
	
	@Transient
	private int birthCounter;
	
	@Transient
	private int controlNumber;
	
	protected SocialSecurityNumber() {
		super(	BigIntTypeDescriptor.INSTANCE,
				SocialSecurityNumberTypeDescriptor.INSTANCE);
	}
	
	public SocialSecurityNumber(
			final BigDecimal sqlBigInt) {
		super(	BigIntTypeDescriptor.INSTANCE,
				SocialSecurityNumberTypeDescriptor.INSTANCE);
		
		final String number = sqlBigInt.setScale(0).toString();
		
		if (number.length() != 11)
				throw new IllegalArgumentException(
						"A social security number has 11 digits");
		
		try {
			birthYear = Integer.parseInt(number.substring(0, 2));
			birthMonth = Integer.parseInt(number.substring(2, 4));
			birthDay = Integer.parseInt(number.substring(4, 6));
			birthCounter = Integer.parseInt(number.substring(6, 9));
			controlNumber = Integer.parseInt(number.substring(9));
		}
		catch (final NumberFormatException nfe) {
			throw new IllegalArgumentException(
					"A social security number consists of only digits");
		}
	}
	
	public SocialSecurityNumber(
			final int birthYear,
			final int birthMonth,
			final int birthDay,
			final int birthCounter,
			final int controlNumber) {
		super(	BigIntTypeDescriptor.INSTANCE,
				SocialSecurityNumberTypeDescriptor.INSTANCE);
		
		this.birthYear = birthYear;
		this.birthMonth = birthMonth;
		this.birthDay = birthDay;
		this.birthCounter = birthCounter;
		this.controlNumber = controlNumber;
	}
	
	public int getBirthYear() {
		return birthYear;
	}
	public int getBirthMonth() {
		return birthMonth;
	}
	public int getBirthDay() {
		return birthDay;
	}
	public int getBirthCounter() {
		return birthCounter;
	}
	public int getBaseNumber() {
		return Integer.parseInt(
				"" + birthYear + birthMonth + birthDay + birthCounter);
	}
	public int getControlNumber() {
		return controlNumber;
	}

	@Override
	public String getName() {
		return MAPPER_NAME;
	}
	
	@Override
	protected boolean registerUnderJavaType() {
		return true;
	}
	
	@Override
	public String toString() {
		return "" +
				birthYear + birthMonth + birthDay +
				birthCounter + controlNumber;
	}
	
	public BigDecimal toBigDecimal() {
		return new BigDecimal(toString());
	}
	
	@Override
	public boolean equals(final Object object) {
		if (this == object) return true;
		
		if (!(object instanceof SocialSecurityNumber)) return false;
		
		final SocialSecurityNumber number = (SocialSecurityNumber)object;
		
		return	birthYear == number.birthYear &&
				birthMonth == number.birthMonth &&
				birthDay == number.birthDay &&
				birthCounter == number.birthCounter &&
				controlNumber == number.controlNumber;
	}
	
	@Override
	public int hashCode() {
		return (int)(37 * (	birthYear + birthMonth + birthDay +
							birthCounter + controlNumber));
	}
}
