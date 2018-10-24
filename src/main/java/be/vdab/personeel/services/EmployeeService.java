package be.vdab.personeel.services;

import java.util.Optional;

import be.vdab.personeel.entities.Employee;

public interface EmployeeService {

	public Optional<Employee> findHighestRanking();
}
