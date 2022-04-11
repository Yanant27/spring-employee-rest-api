package hyk.springframework.springemployeerestapi.service;

import hyk.springframework.springemployeerestapi.api.v1.mapper.EmployeeMapper;
import hyk.springframework.springemployeerestapi.api.v1.model.EmployeeDTO;
import hyk.springframework.springemployeerestapi.controller.v1.EmployeeController;
import hyk.springframework.springemployeerestapi.domain.Employee;
import hyk.springframework.springemployeerestapi.exception.ResourceNotFoundException;
import hyk.springframework.springemployeerestapi.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @author Htoo Yanant Khin
 */
class EmployeeServiceImplTest {

    @Mock
    EmployeeRepository employeeRepository;

    EmployeeMapper mapper = EmployeeMapper.INSTANCE;

    EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeService =new EmployeeServiceImpl(mapper, employeeRepository);
    }

    @Test
    void getAllEmployees() {
        // Given
        Employee employee1 = new Employee();
        employee1.setFirstName("Jennifer");
        employee1.setLastName("Whalen");
        employee1.setEmail("jennifer@gmail.com");

        Employee employee2 = new Employee();
        employee2.setFirstName("Michael");
        employee2.setLastName("Hartstein");
        employee2.setEmail("michael@gmail.com");

        Employee employee3 = new Employee();
        employee3.setFirstName("Susan");
        employee3.setLastName("Mavris");
        employee3.setEmail("susan@gmail.com");

        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2, employee3));

        // When
        List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployees();

        // Then
        assertEquals(3, employeeDTOList.size());
    }

    @Test
    void getEmployeeById() {
        Long id = 1L;

        // Given
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName("Jennifer");
        employee.setLastName("Whalen");
        employee.setEmail("jennifer@gmail.com");

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        // When
        EmployeeDTO employeeDTO =employeeService.getEmployeeById(id);

        // Then
        assertEquals("Jennifer", employeeDTO.getFirstName());
        assertEquals("Whalen", employeeDTO.getLastName());
        assertEquals("jennifer@gmail.com", employeeDTO.getEmail());
        assertEquals(EmployeeController.BASE_URL + "/" + id, employeeDTO.getEmployeeUrl());
    }

    @Test
    void getEmployeeByIdNotFound() {
        Long id = 1L;

        //Given
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.getEmployeeById(id);
        });

        // Then
        String expectedMessage = "Employee not found with ID = " + id;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void createNewEmployee() {
        Long id = 1L;

        // Given
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("Jennifer");
        employeeDTO.setLastName("Whalen");
        employeeDTO.setEmail("jennifer@gmail.com");

        Employee savedEmployee = new Employee();
        savedEmployee.setId(id);
        savedEmployee.setFirstName(employeeDTO.getFirstName());
        savedEmployee.setLastName(employeeDTO.getLastName());
        savedEmployee.setEmail(employeeDTO.getEmail());

        when(employeeRepository.save(any(Employee.class))).thenReturn(savedEmployee);

        // When
        EmployeeDTO savedDto = employeeService.createNewEmployee(employeeDTO);

        // Then
        assertEquals(employeeDTO.getFirstName(), savedDto.getFirstName());
        assertEquals(employeeDTO.getLastName(), savedDto.getLastName());
        assertEquals(employeeDTO.getEmail(), savedDto.getEmail());
        assertEquals(EmployeeController.BASE_URL + "/" + id, savedDto.getEmployeeUrl());
    }

    @Test
    void updateEmployee() {
        Long id = 1L;

        // Given
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("Jennifer");
        employeeDTO.setLastName("Whalen");
        employeeDTO.setEmail("jennifer@gmail.com");

        Employee savedEmployee = new Employee();
        savedEmployee.setId(id);
        savedEmployee.setFirstName(employeeDTO.getFirstName());
        savedEmployee.setLastName(employeeDTO.getLastName());
        savedEmployee.setEmail(employeeDTO.getEmail());

        when(employeeRepository.save(any(Employee.class))).thenReturn(savedEmployee);

        // When
        EmployeeDTO savedDto = employeeService.updateEmployee(id, employeeDTO);

        // Then
        assertEquals(employeeDTO.getFirstName(), savedDto.getFirstName());
        assertEquals(employeeDTO.getLastName(), savedDto.getLastName());
        assertEquals(employeeDTO.getEmail(), savedDto.getEmail());
        assertEquals(EmployeeController.BASE_URL + "/" + id, savedDto.getEmployeeUrl());
    }

    @Test
    void deleteEmployee() {
        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).deleteById(anyLong());
    }
}