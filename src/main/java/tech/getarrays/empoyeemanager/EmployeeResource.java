package tech.getarrays.empoyeemanager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.empoyeemanager.model.Employee;
import tech.getarrays.empoyeemanager.model.Team;
import tech.getarrays.empoyeemanager.service.EmployeeService;
import tech.getarrays.empoyeemanager.service.TeamService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {
    public EmployeeResource(EmployeeService employeeService,TeamService teamService) {
        this.employeeService = employeeService;
        this.teamService=teamService;
    }

    private final EmployeeService employeeService;
    private final TeamService teamService;
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

    public ResponseEntity<Team> updateEmployee(@RequestBody Employee employee,@PathVariable("id")Long id){
    Employee oldEmployee= employeeService.findEmployeeById(id);

    if(employee.getName()!=null){oldEmployee.setName(employee.getName());}

    if(employee.getEmail()!=null){oldEmployee.setEmail(employee.getEmail());}

    if(employee.getPhone()!=null){oldEmployee.setPhone(employee.getPhone());}

    if(employee.getJobTitle()!=null){oldEmployee.setJobTitle(employee.getJobTitle());}

    if(employee.getImageUrl()!=null){oldEmployee.setImageUrl(employee.getImageUrl());}

if(employee.getTeamId()!=null){
Team existigTeam= teamService.findTeamByTeamId(employee.getTeamId().getTeamId());
employee.setTeamId(existigTeam);
}
        Team existigTeam= teamService.findTeamByTeamId(employee.getTeamId().getTeamId());
        oldEmployee.setTeamId(existigTeam);

        Employee updateEmployee = employeeService.updateEmployee(oldEmployee);
        return new ResponseEntity<>(existigTeam, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
   employeeService.deleteEmployee(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}

