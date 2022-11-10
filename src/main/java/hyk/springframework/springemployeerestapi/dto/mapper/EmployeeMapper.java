package hyk.springframework.springemployeerestapi.dto.mapper;

import hyk.springframework.springemployeerestapi.dto.EmployeeDTO;
import hyk.springframework.springemployeerestapi.domain.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Maps Employee and EmployeeDTO using Mapstruct
 *
 * @author Htoo Yanant Khin
 */
@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDTO employeeToEmployeeDto(Employee employee);

    Employee employeeDtoToEmployee(EmployeeDTO employeeDTO);
}
