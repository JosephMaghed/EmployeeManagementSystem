package tech.getarrays.empoyeemanager.Controller;
import java.lang.Long;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.empoyeemanager.model.Team;
import tech.getarrays.empoyeemanager.service.TeamService;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    public TeamController(TeamService teamService) {
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
    @GetMapping("/find/{id}")
    public ResponseEntity <Team> getTeamById(@PathVariable("id")Long id){
       Team  teams=teamService.findTeamById(id);
        return new ResponseEntity<>(teams,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")

    public ResponseEntity<Team> updateEmployee(@RequestBody Team team,@PathVariable("id")Long id){
        Team existingTeam= teamService.findTeamById(id);

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
