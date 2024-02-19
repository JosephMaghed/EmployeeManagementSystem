package tech.getarrays.empoyeemanager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.empoyeemanager.model.Employee;
import tech.getarrays.empoyeemanager.model.Team;
import tech.getarrays.empoyeemanager.service.EmployeeService;
import tech.getarrays.empoyeemanager.service.TeamService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/team")
public class TeamResource {
    public TeamResource(TeamService teamService) {
        this.teamService = teamService;
    }
    private final TeamService teamService;

    @PostMapping("/add")
    public ResponseEntity<Team> addTeam(@RequestBody Team team){
        Team newTeam;
        newTeam = teamService.addTeam(team);
        return new ResponseEntity<>(newTeam, HttpStatus.CREATED);

    }
    @GetMapping("/all")
    public ResponseEntity<List<Team>> getAllTeams(){
        List<Team> Teams=teamService.findAllTeams();
        return new ResponseEntity<>(Teams,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity <Optional<Team>> getTeamById(@PathVariable("id")Long id){
       Optional<Team>  teams=teamService.findTeamById(id);
        return new ResponseEntity<>(teams,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")

    public ResponseEntity<Team> updateEmployee(@RequestBody Team team,@PathVariable("id")Long id){
        Team existingTeam= teamService.findTeamByTeamId(id);

        if(team.getTeamLeaderId()!=null) {
            existingTeam.setTeamLeaderId(team.getTeamLeaderId());
        }
        if(team.getTeamRole()!=null) {
            existingTeam.setTeamRole(team.getTeamRole());
        }



        Team updateTeam = teamService.updateTeam(existingTeam);
        return new ResponseEntity<>(updateTeam, HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteTeam(@PathVariable("id")Long id){
       teamService.deleteTeam(id);
        return new ResponseEntity<>( HttpStatus.OK);

    }
}
