package be.vdab.personeel.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "jobtitels")
public class JobTitle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "naam")
	private String name;
	
	@Version
	@Column(name = "versie")
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
}
