package be.vdab.personeel.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.personeel.entities.Employee;
import be.vdab.personeel.entities.JobTitle;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	@EntityGraph(Employee.WITH_SUPERVISOR)
	public Optional<Employee> findById(final long employeeId);
	
	//@Query(Employee.HIGHEST_RANKING)
	public Optional<Employee> findBySupervisorIsNull();
	public Optional<Employee> findByEmailAddress(final String emailAddress);
	
	public List<Employee> findAllBySupervisor(final Employee supervisor);
	
	public List<Employee> findAllByJobTitle(final JobTitle jobTitle);
}
