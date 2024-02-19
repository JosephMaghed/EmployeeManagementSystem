package tech.getarrays.empoyeemanager.model;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//How to generate value
    @Column(nullable = false,updatable = false)
    private Long teamId;
    private Long TeamLeaderId;
    private String TeamRole;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", TeamLeaderId=" + TeamLeaderId +
                ", TeamRole='" + TeamRole + '\'' +
                '}';
    }

    public Long getTeamLeaderId() {
        return TeamLeaderId;
    }

    public void setTeamLeaderId(Long teamLeaderId) {
        TeamLeaderId = teamLeaderId;
    }

    public String getTeamRole() {
        return TeamRole;
    }

    public void setTeamRole(String teamRole) {
        TeamRole = teamRole;
    }
}
