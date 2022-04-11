package hyk.springframework.springemployeerestapi.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Htoo Yanant Khin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    @Column(name = "first_name")
//    @NotEmpty
    @Size(min = 1, max = 50, message = "First name must be between {min} and {max} characters")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    @Size(min = 1, max = 50, message = "Last name must be between {min} and {max} characters")
    private String lastName;

    @Column(name = "email")
    @Email // Simply checks whether string include '@' or not
//    @Pattern() // Use to check specific email format with regex
    private String email;

    @JsonProperty("employee_url")
    private String employeeUrl;
}
