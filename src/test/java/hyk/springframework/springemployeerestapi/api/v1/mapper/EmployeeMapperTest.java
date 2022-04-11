package hyk.springframework.springemployeerestapi.api.v1.mapper;

import hyk.springframework.springemployeerestapi.api.v1.model.EmployeeDTO;
import hyk.springframework.springemployeerestapi.domain.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Htoo Yanant Khin
 */
class EmployeeMapperTest {
    public static final String FIRSTNAME = "Jennifer";
    public static final String LASTNAME = "Whalen";
    public static final String EMAIL = "jennifer@gmail.com";
    EmployeeMapper employeeMapper = EmployeeMapper.INSTANCE;

    @Test
    void employeeToEmployeeDto() {
        // Given
        Employee employee = new Employee();
        employee.setFirstName(FIRSTNAME);
        employee.setLastName(LASTNAME);
        employee.setEmail(EMAIL);

        // When
        EmployeeDTO employeeDTO = employeeMapper.employeeToEmployeeDto(employee);

        // Then
        assertEquals(FIRSTNAME, employeeDTO.getFirstName());
        assertEquals(LASTNAME, employeeDTO.getLastName());
        assertEquals(EMAIL, employeeDTO.getEmail());
    }

    @Test
    void employeeDtoToEmployee() {
        // Given
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName(FIRSTNAME);
        employeeDTO.setLastName(LASTNAME);
        employeeDTO.setEmail(EMAIL);

        // When
        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDTO);

        // Then
        assertEquals(FIRSTNAME, employee.getFirstName());
        assertEquals(LASTNAME, employee.getLastName());
        assertEquals(EMAIL, employee.getEmail());
    }
}