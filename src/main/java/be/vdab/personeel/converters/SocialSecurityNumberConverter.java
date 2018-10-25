package be.vdab.personeel.converters;

import java.math.BigDecimal;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import be.vdab.personeel.valueobjects.SocialSecurityNumber;

@Converter(autoApply = true)
public class SocialSecurityNumberConverter
implements AttributeConverter<SocialSecurityNumber, BigDecimal> {

//	/** Implements Serializable */
//	private static final long serialVersionUID = -4635254872941040510L;

	public SocialSecurityNumberConverter() {}

	@Override
	public BigDecimal convertToDatabaseColumn(
			final SocialSecurityNumber socialSecurityNumber) {
		return socialSecurityNumber.toBigDecimal();
	}

	@Override
	public SocialSecurityNumber convertToEntityAttribute(
			final BigDecimal dbData) {
		return new SocialSecurityNumber(dbData);
	}
}
