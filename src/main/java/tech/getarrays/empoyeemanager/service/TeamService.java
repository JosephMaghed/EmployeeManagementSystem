package tech.getarrays.empoyeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.empoyeemanager.model.Team;
import tech.getarrays.empoyeemanager.repo.TeamRepo;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired

    public TeamService(TeamRepo teamRepo) {
        this.teamRepo=teamRepo;
    }
    private  final TeamRepo teamRepo;

    public Team addTeam(Team team){
        return  teamRepo.save(team);
    }
    public List<Team> findAllTeams(){
        return teamRepo.findAll();
    }
    public Optional<Team> findTeamById(Long id){
        return teamRepo.findById(id);
    }
    public Team findTeamByTeamId(Long id){
        return teamRepo.findByteamId(id);
    }
    public Team updateTeam(Team team){
        return  teamRepo.save(team);
    }
    public void deleteTeam(Long id){
         teamRepo.deleteById(id);
    }
}
