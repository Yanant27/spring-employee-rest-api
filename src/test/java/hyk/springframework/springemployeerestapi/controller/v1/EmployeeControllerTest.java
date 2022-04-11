package hyk.springframework.springemployeerestapi.controller.v1;

import hyk.springframework.springemployeerestapi.api.v1.model.EmployeeDTO;
import hyk.springframework.springemployeerestapi.domain.Employee;
import hyk.springframework.springemployeerestapi.exception.RestResponseEntityExceptionHandler;
import hyk.springframework.springemployeerestapi.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static hyk.springframework.springemployeerestapi.controller.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Htoo Yanant Khin
 */
class EmployeeControllerTest {
    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getListOfEmployees() throws Exception {
        // Given
        EmployeeDTO employee1 = new EmployeeDTO();
        employee1.setFirstName("Jennifer");
        employee1.setLastName("Whalen");
        employee1.setEmail("jennifer@gmail.com");
        employee1.setEmployeeUrl(EmployeeController.BASE_URL + "/1");

        EmployeeDTO employee2 = new EmployeeDTO();
        employee2.setFirstName("Michael");
        employee2.setLastName("Hartstein");
        employee2.setEmail("michael@gmail.com");
        employee2.setEmployeeUrl(EmployeeController.BASE_URL + "/2");

        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee1, employee2));

        // When
        mockMvc.perform(get(EmployeeController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employees", hasSize(2)));
    }

    @Test
    void getEmployeeById() throws Exception {
        Long id = 1L;
        // Given
        EmployeeDTO employee = new EmployeeDTO();
        employee.setFirstName("Jennifer");
        employee.setLastName("Whalen");
        employee.setEmail("jennifer@gmail.com");
        employee.setEmployeeUrl(EmployeeController.BASE_URL + "/" + id);

        when(employeeService.getEmployeeById(id)).thenReturn(employee);

        // When
        mockMvc.perform(get(EmployeeController.BASE_URL + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Jennifer")))
                .andExpect(jsonPath("$.lastName", equalTo("Whalen")));
//                .andExpect(jsonPath("$.lastname", equalTo("Whalen")));
    }

    @Test
    void createNewEmployee() throws Exception {
        Long id = 1L;
        // Given
        EmployeeDTO employee = new EmployeeDTO();
        employee.setFirstName("Jennifer");
        employee.setLastName("Whalen");
        employee.setEmail("jennifer@gmail.com");
        employee.setEmployeeUrl(EmployeeController.BASE_URL + "/" + id);

        // Given
        EmployeeDTO returnDto = new EmployeeDTO();
        returnDto.setFirstName(employee.getFirstName());
        returnDto.setLastName(employee.getLastName());
        returnDto.setEmail(employee.getEmail());
        returnDto.setEmployeeUrl(employee.getEmployeeUrl());

        when(employeeService.createNewEmployee(employee)).thenReturn(returnDto);

        // When/ Then
        mockMvc.perform(post(EmployeeController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(employee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Jennifer")))
                .andExpect(jsonPath("$.lastName", equalTo("Whalen")))
                .andExpect(jsonPath("$.employee_url", equalTo(EmployeeController.BASE_URL + "/" + id)));
    }

    @Test
    void updateEmployee() throws Exception {
        Long id = 1L;
        // Given
        EmployeeDTO employee = new EmployeeDTO();
        employee.setFirstName("Jennifer");
        employee.setLastName("Whalen");
        employee.setEmail("jennifer@gmail.com");
        employee.setEmployeeUrl(EmployeeController.BASE_URL + "/" + id);

        // Given
        EmployeeDTO returnDto = new EmployeeDTO();
        returnDto.setFirstName(employee.getFirstName());
        returnDto.setLastName(employee.getLastName());
        returnDto.setEmail(employee.getEmail());
        returnDto.setEmployeeUrl(employee.getEmployeeUrl());

        when(employeeService.updateEmployee(anyLong(), any(EmployeeDTO.class))).thenReturn(returnDto);

        // When/ Then
        mockMvc.perform(put(EmployeeController.BASE_URL + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Jennifer")))
                .andExpect(jsonPath("$.lastName", equalTo("Whalen")))
                .andExpect(jsonPath("$.employee_url", equalTo(EmployeeController.BASE_URL + "/" + id)));
    }

    @Test
    void deleteEmployee() throws Exception {
        mockMvc.perform(delete(EmployeeController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(employeeService).deleteEmployee(anyLong());
    }
}