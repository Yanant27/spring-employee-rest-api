package hyk.springframework.springemployeerestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Htoo Yanant Khin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeListDTO {
    List<EmployeeDTO> employees;
}
