package hyk.springframework.springemployeerestapi.bootstrap;

import hyk.springframework.springemployeerestapi.domain.Employee;
import hyk.springframework.springemployeerestapi.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;

    public Bootstrap(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadEmployeeData();
    }

    /**
     * Create sample <code>Employee</code> objects and save into <code>EmployeeRepository</code>
     * for testing purpose.
     */
    private void loadEmployeeData() {
        // Create 5 employees
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

        Employee employee4 = new Employee();
        employee4.setFirstName("Shelley");
        employee4.setLastName("Higgins");
        employee4.setEmail("shelley@gmail.com");

        Employee employee5 = new Employee();
        employee5.setFirstName("Steven");
        employee5.setLastName("King");
        employee5.setEmail("steven@gmail.com");

        // Save all employees into EmployeeRepository
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);
        employeeRepository.save(employee5);

        System.out.println("Employees Data Loaded. Total = " + employeeRepository.count());
    }
}
