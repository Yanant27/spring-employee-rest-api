package hyk.springframework.springemployeerestapi.service;

import hyk.springframework.springemployeerestapi.dto.EmployeeDTO;

import java.util.List;

/**
 * @author Htoo Yanant Khin
 */
public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long id);

    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO updateEmployee(Long id, EmployeeDTO employee);

    void deleteEmployee(Long id);
}
