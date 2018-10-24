package be.vdab.personeel.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.personeel.entities.Employee;
import be.vdab.personeel.repositories.EmployeeRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class EmployeeServiceDefault implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	
	public EmployeeServiceDefault(final EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public Optional<Employee> findHighestRanking() {
		//return employeeRepository.findHighestRanking();
		return employeeRepository.findBySupervisorIsNull();
	}

}
