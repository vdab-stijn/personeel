package be.vdab.personeel.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "jobtitels")
public class JobTitle implements Serializable {

	/** Implements Serializable */
	private static final long serialVersionUID = -4635254872941040510L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "naam")
	private String name;
	
	// Optimistic
	@Version
	@Column(name = "versie", nullable = false)
	private long version;
	
	protected JobTitle() {}
	public JobTitle(final String name) {
		setName(name);
	}
	
	public long getId() {
		return id;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public String toString() {
		return getName();
	}
}
