package tech.getarrays.empoyeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.empoyeemanager.exception.UserNotFoundException;
import tech.getarrays.empoyeemanager.model.Team;
import tech.getarrays.empoyeemanager.repo.TeamRepo;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private  final TeamRepo teamRepo;

    //Constructor
    @Autowired
    public TeamService(TeamRepo teamRepo) {
        this.teamRepo=teamRepo;
    }

    public Team addTeam(Team team){
        return  teamRepo.save(team);
    }

    public List<Team> findAllTeams(){
        return teamRepo.findAll();
    }

    public Team findTeamById(Long id){
        return teamRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not fond"));}

    public Team updateTeam(Team team){
        return  teamRepo.save(team);
    }

    public void deleteTeam(Long id){
         teamRepo.deleteById(id);
    }
}
