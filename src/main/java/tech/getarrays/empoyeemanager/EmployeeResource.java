package tech.getarrays.empoyeemanager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.empoyeemanager.model.Employee;
import tech.getarrays.empoyeemanager.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {
    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private final EmployeeService employeeService;
@GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees(){
    List<Employee> employees=employeeService.findAllEmployees();
    return new ResponseEntity<>(employees, HttpStatus.OK);
}
    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id")Long id){
        Employee employee=employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @PostMapping("/add")

    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee newEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")

    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,@PathVariable("id")Long id){
    Employee oldEmployee= employeeService.findEmployeeById(id);

    if(employee.getName()!=null){oldEmployee.setName(employee.getName());}

    if(employee.getEmail()!=null){oldEmployee.setEmail(employee.getEmail());}

    if(employee.getPhone()!=null){oldEmployee.setPhone(employee.getPhone());}

    if(employee.getJobTitle()!=null){oldEmployee.setJobTitle(employee.getJobTitle());}

    if(employee.getImageUrl()!=null){oldEmployee.setImageUrl(employee.getImageUrl());}

        Employee updateEmployee = employeeService.updateEmployee(oldEmployee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
   employeeService.deleteEmployee(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}

