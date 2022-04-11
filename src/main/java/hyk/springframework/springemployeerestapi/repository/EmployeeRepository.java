package hyk.springframework.springemployeerestapi.repository;

import hyk.springframework.springemployeerestapi.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Htoo Yanant Khin
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
