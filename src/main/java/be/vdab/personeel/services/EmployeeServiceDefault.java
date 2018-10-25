package be.vdab.personeel.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.personeel.entities.Employee;
import be.vdab.personeel.entities.JobTitle;
import be.vdab.personeel.repositories.EmployeeRepository;
import be.vdab.personeel.valueobjects.SocialSecurityNumber;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class EmployeeServiceDefault implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	
	public EmployeeServiceDefault(final EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	public Optional<Employee> read(final long employeeId) {
		return employeeRepository.findById(employeeId);
	}
	
	@Override
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	public Optional<Employee> findHighestRanking() {
		//return employeeRepository.findHighestRanking();
		return employeeRepository.findBySupervisorIsNull();
	}

	@Override
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	public List<Employee> findSubordinates(final Employee supervisor) {
		return employeeRepository.findAllBySupervisor(supervisor);
	}
	
	@Override
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	public List<Employee> findByJobTitle(final JobTitle jobTitle) {
		return employeeRepository.findAllByJobTitle(jobTitle);
	}
	
	@Override
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void raiseSalary(final long employeeId, final BigDecimal raise) {
		employeeRepository.save(read(employeeId).get().raiseSalary(raise));
	}
	
	@Override
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void saveSSN(
			final long employeeId, final SocialSecurityNumber number) {
		employeeRepository.save(read(employeeId).get().saveSSN(number));
	}
}
