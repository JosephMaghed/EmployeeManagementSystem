package tech.getarrays.empoyeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.empoyeemanager.model.Employee;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
  Employee findEmployeeById(Long id);
}
