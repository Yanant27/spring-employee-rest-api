package hyk.springframework.springemployeerestapi.service;

import hyk.springframework.springemployeerestapi.api.v1.mapper.EmployeeMapper;
import hyk.springframework.springemployeerestapi.api.v1.model.EmployeeDTO;
import hyk.springframework.springemployeerestapi.controller.v1.EmployeeController;
import hyk.springframework.springemployeerestapi.domain.Employee;
import hyk.springframework.springemployeerestapi.exception.ResourceNotFoundException;
import hyk.springframework.springemployeerestapi.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Get data from <code>EmployeeRepository</code> and perform operations such as
 * CRUD, filtering data based on some logic, etc.
 *
 * @author Htoo Yanant Khin
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeMapper employeeMapper, EmployeeRepository employeeRepository) {
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
    }

    /**
     * Retrieve all employees from <code>Employees</code> table.
     *
     * @return list of all employees
     */
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository
                .findAll()
                .stream()
                .map(employee -> {
                    EmployeeDTO employeeDTO = employeeMapper.employeeToEmployeeDto(employee);
                    employeeDTO.setEmployeeUrl(getEmployeeUrl(employee.getId()));
                    return employeeDTO;
                }).collect(Collectors.toList());
    }

    /**
     * Retrieve one employee with requested <i>id</i>.
     *
     * @param id requested employee ID for target data
     * @return   target employee data
     */
    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    EmployeeDTO employeeDTO = employeeMapper.employeeToEmployeeDto(employee);
                    employeeDTO.setEmployeeUrl(getEmployeeUrl(employee.getId()));
                    return employeeDTO;
                }).orElseThrow(()->new ResourceNotFoundException("Employee not found with ID = " + id));
    }

    /**
     * Create new employee with data from request body.
     *
     * @param employeeDTO new employee data from request body
     * @return            newly created employee
     */
    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        return saveAndReturnEmployeeDTO(employeeMapper.employeeDtoToEmployee(employeeDTO));
    }

    /**
     * Retrieve one employee with requested <i>id</i> and update with new data from request body.
     *
     * @param id          requested employee ID for target data
     * @param employeeDTO new data from request body
     * @return            updated employee
     */
    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDTO);
        employee.setId(id);
        return saveAndReturnEmployeeDTO(employee);
    }

    /**
     * Delete one employee with requested <i>id</i>.
     *
     * @param id requested employee ID for target data
     */
    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeDTO findEmployeeInRepository(Long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    EmployeeDTO employeeDTO = employeeMapper.employeeToEmployeeDto(employee);
                    employeeDTO.setEmployeeUrl(getEmployeeUrl(employee.getId()));
                    return employeeDTO;
                }).orElseThrow(()->new ResourceNotFoundException("Employee not found with ID = " + id));
    }
    /**
     * Create URL with requested <i>id</i>.
     *
     * @param id requested employee ID for target URL
     * @return   newly created URL
     */
    private String getEmployeeUrl(Long id) {
        return EmployeeController.BASE_URL + "/" + id;
    }

    /**
     * Save employee from parameter into <code>EmployeeRepository</code>
     * and convert it into EmployeeDTO.
     *
     * @param employee target employee object
     * @return         converted employee DTO
     */
    private EmployeeDTO saveAndReturnEmployeeDTO(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDTO returnEmployeeDTO = employeeMapper.employeeToEmployeeDto(savedEmployee);
        returnEmployeeDTO.setEmployeeUrl(getEmployeeUrl(savedEmployee.getId()));
        return returnEmployeeDTO;
    }
}
