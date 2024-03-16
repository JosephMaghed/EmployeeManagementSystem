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
    //Retrieve all employees
    List<Employee> employees=employeeService.findAllEmployees();
    return new ResponseEntity<>(employees, HttpStatus.OK);}

    //Get employee by id
    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id")Long id){
    //Retrieve employee by ID
        Employee employee=employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);}

    //Add new employee
    @PostMapping("/add")

    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee newEmployee = employeeService.addEmployee(employee);

        //Retrieve TeamId & add it to employee entity as a foreign key
        if(employee.getTeam().getTeamId()!=null){
            Team existigTeam= teamService.findTeamById(employee.getTeam().getTeamId());
            employee.setTeam(existigTeam);
        }
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);

    }

    //update an employee by id
    @PutMapping("/update/{id}")

    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,@PathVariable("id")Long id){
    Employee oldEmployee= employeeService.findEmployeeById(id);

    //Update fields only if they are not null

    if(employee.getName()!=null){oldEmployee.setName(employee.getName());}

    if(employee.getEmail()!=null){oldEmployee.setEmail(employee.getEmail());}

    if(employee.getPhone()!=null){oldEmployee.setPhone(employee.getPhone());}

    if(employee.getJobTitle()!=null){oldEmployee.setJobTitle(employee.getJobTitle());}

    if(employee.getImageUrl()!=null){oldEmployee.setImageUrl(employee.getImageUrl());}

//Check if team ID is not null if true retrieve team by team id & add it as a foreign key to employee

if(employee.getTeam().getTeamId()!=null){
        Team existigTeam= teamService.findTeamById(employee.getTeam().getTeamId());
        employee.setTeam(existigTeam);
}
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

