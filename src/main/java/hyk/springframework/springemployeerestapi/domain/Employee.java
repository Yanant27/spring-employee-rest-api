package hyk.springframework.springemployeerestapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Simple JavaBean domain object representing an employee.
 *
 * @author Htoo Yanant Khin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee extends BasedEntity {
    private String firstName;
    private String lastName;
    private String email;
}
