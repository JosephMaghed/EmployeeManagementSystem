package tech.getarrays.empoyeemanager.model;

import jakarta.persistence.*;

import java.io.Serializable;
//Use @Entity to make sure that this class gets mapped to any database
@Entity
public class Employee implements Serializable {
@Id//Primary Key
@GeneratedValue(strategy= GenerationType.IDENTITY)//How to generate value
@Column(nullable = false,updatable = false)
    private Long id;
    private String name;
    private String email;
    private String jobTitle;
    private String phone;
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "TeamId")  // Name of the foreign key column
    private Team team;
    public Team getTeamId() {
        return team;
    }



    public void setTeamId(Team teamId) {
        team = teamId;
    }

    @Column(nullable = false,updatable = false)
    private String employeeCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", phone='" + phone + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", TeamId=" + team +
                ", employeeCode='" + employeeCode + '\'' +
                '}';
    }
}
