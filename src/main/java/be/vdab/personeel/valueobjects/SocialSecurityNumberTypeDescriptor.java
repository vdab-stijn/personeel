package be.vdab.personeel.valueobjects;

import org.hibernate.type.descriptor.java.BigDecimalTypeDescriptor;

public class SocialSecurityNumberTypeDescriptor
extends BigDecimalTypeDescriptor {

	/** Implements Serializable */
	private static final long serialVersionUID = -4635254872941040510L;

	public static final SocialSecurityNumberTypeDescriptor INSTANCE
	=  new SocialSecurityNumberTypeDescriptor();
	
	public SocialSecurityNumberTypeDescriptor() {
		super();
	}
}
