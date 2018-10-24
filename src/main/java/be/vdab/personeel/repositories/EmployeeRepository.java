package be.vdab.personeel.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import be.vdab.personeel.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	//@Query(Employee.HIGHEST_RANKING)
	public Optional<Employee> findBySupervisorIsNull();
}
