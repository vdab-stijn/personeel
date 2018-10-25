package be.vdab.personeel.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.vdab.personeel.adapters.LocalDateAdapter;
import be.vdab.personeel.constraints.SocialSecurityNumberConstraint;
import be.vdab.personeel.valueobjects.SocialSecurityNumber;

@Entity
@Table(name = "werknemers")
/*
@NamedQuery(
		name = Employee.HIGHEST_RANKING,
		query = "SELECT e FROM Employee e WHERE e.supervisor IS NULL")
*/
@TypeDef(
		name = SocialSecurityNumber.MAPPER_NAME,
		typeClass = SocialSecurityNumber.class)
@NamedEntityGraph(
		name = Employee.WITH_SUPERVISOR,
		attributeNodes = @NamedAttributeNode("supervisor"))
public class Employee implements Serializable {

	/** Implements Serializable */
	private static final long serialVersionUID = -4635254872941040510L;
	
	// Selects the employee that has only subordinates, no superiors
	public static final String HIGHEST_RANKING = "Employee.highestRanking";
	// Fetch the supervisor as well.
	public static final String WITH_SUPERVISOR = "Employee.withSupervisor";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@SafeHtml
	@Column(name = "voornaam")
	private String firstName;
	
	@NotBlank
	@SafeHtml
	@Column(name = "familienaam")
	private String lastName;
	
	@NotBlank
	@SafeHtml
	@Email
	@Column(name = "email")
	private String emailAddress;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "chefid")
	private Employee supervisor;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "jobtitelid")
	@NotNull
	private JobTitle jobTitle;
	
	@NotNull
	@PositiveOrZero
	@NumberFormat(style = Style.CURRENCY)
	@Digits(integer = 10, fraction = 2)
	@Column(name = "salaris")
	private BigDecimal salary;
	
	@Column(name = "paswoord")
	private String password;
	
	@DateTimeFormat(style = "S-")
	@NotNull
	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	@Column(name = "geboorte")
	private LocalDate birthDate;
	
	@SocialSecurityNumberConstraint
	@Type(type = SocialSecurityNumber.MAPPER_NAME)
	@Column(name = "rijksregisternr", columnDefinition = "bigint")
	private SocialSecurityNumber socialSecurityNumber;
	
	// Optimistic
	@Version
	@Column(name = "versie", nullable = false)
	private long version;
	
	@OneToMany(mappedBy = "supervisor")
	@XmlTransient
	@JsonIgnore
	private Set<Employee> subordinates = new LinkedHashSet<>();
	
	protected Employee() {}
	public Employee(
			final String firstName,
			final String lastName,
			final String emailAddress,
			final Employee supervisor,
			final JobTitle jobTitle,
			final BigDecimal salary,
			final String password,
			final LocalDate birthDate,
			final SocialSecurityNumber socialSecurityNumber) {
		setFirstName(firstName);
		setLastName(lastName);
		setEmailAddress(emailAddress);
		setSupervisor(supervisor);
		setJobTitle(jobTitle);
		setSalary(salary);
		setPassword(password);
		setBirthDate(birthDate);
		setSocialSecurityNumber(socialSecurityNumber);
	}
	
	public long getId() {
		return id;
	}
	
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public void setEmailAddress(final String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setSupervisor(final Employee supervisor) {
		this.supervisor = supervisor;
	}
	public Employee getSupervisor() {
		return supervisor;
	}
	
	public void setJobTitle(final JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}
	public JobTitle getJobTitle() {
		return jobTitle;
	}
	
	public void setSalary(final BigDecimal salary) {
		this.salary = salary;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	
	public void setPassword(final String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	
	public void setBirthDate(final LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	public void setSocialSecurityNumber(
			final SocialSecurityNumber socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}
	public SocialSecurityNumber getSocialSecurityNumber() {
		return socialSecurityNumber;
	}
	
	public Set<Employee> getSubordinates() {
		return subordinates;
	}
	
	public Employee raiseSalary(final BigDecimal augend) {
		salary = salary.add(augend);
		
		return this;
	}
	
	public Employee saveSSN(final SocialSecurityNumber socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
		
		return this;
	}
}
