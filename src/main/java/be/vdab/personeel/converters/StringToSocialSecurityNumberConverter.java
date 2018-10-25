package be.vdab.personeel.converters;

import org.springframework.core.convert.converter.Converter;

import be.vdab.personeel.valueobjects.SocialSecurityNumber;

public class StringToSocialSecurityNumberConverter
implements Converter<String, SocialSecurityNumber>{

	@Override
	public SocialSecurityNumber convert(final String source) {
		return new SocialSecurityNumber(source);
	}

}
