package tech.getarrays.empoyeemanager.Controller;

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
public class EmployeeController {
    //Constructor
    public EmployeeController(EmployeeService employeeService, TeamService teamService) {
        this.employeeService = employeeService;
        this.teamService=teamService;
    }


    private final EmployeeService employeeService;
    private final TeamService teamService;

    //Get all employee data
@GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees(){
    List<Employee> employees=employeeService.findAllEmployees();
    return new ResponseEntity<>(employees, HttpStatus.OK);}

    //Get employee by id
    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id")Long id){
        Employee employee=employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);}

    //Add new employee
    @PostMapping("/add")

    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee newEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    //update an employee by id
    @PutMapping("/update/{id}")

    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,@PathVariable("id")Long id){
    Employee oldEmployee= employeeService.findEmployeeById(id);

    if(employee.getName()!=null){oldEmployee.setName(employee.getName());}

    if(employee.getEmail()!=null){oldEmployee.setEmail(employee.getEmail());}

    if(employee.getPhone()!=null){oldEmployee.setPhone(employee.getPhone());}

    if(employee.getJobTitle()!=null){oldEmployee.setJobTitle(employee.getJobTitle());}

    if(employee.getImageUrl()!=null){oldEmployee.setImageUrl(employee.getImageUrl());}

if(employee.getTeamId()!=null){
Team existigTeam= teamService.findTeamById(employee.getTeamId().getTeamId());
employee.setTeamId(existigTeam);
}

        Team existigTeam= teamService.findTeamById(employee.getTeamId().getTeamId());
        oldEmployee.setTeamId(existigTeam);

        Employee updateEmployee = employeeService.updateEmployee(oldEmployee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }
//Find Employees associated in each Team
@GetMapping("find/teamId/{id}")
public ResponseEntity<List<Employee>> getEmployeeByTeamId(@PathVariable("id")Long id){
    List<Employee> employees=employeeService.findEmployeeByTeamId(id);
    return new ResponseEntity<>(employees, HttpStatus.OK);}
    //Delete Employee
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
   employeeService.deleteEmployee(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}

