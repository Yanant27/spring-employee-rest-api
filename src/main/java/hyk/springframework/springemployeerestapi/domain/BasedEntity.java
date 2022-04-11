package hyk.springframework.springemployeerestapi.domain;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Simple JavaBean domain object with <i>id</i> property. <br>
 * Used as a base class for objects needing this property.
 *
 * @author Htoo Yanant Khin
 */
@Data
@MappedSuperclass
public class BasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
