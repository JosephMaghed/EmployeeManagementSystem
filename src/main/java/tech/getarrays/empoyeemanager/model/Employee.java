package tech.getarrays.empoyeemanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
//Use @Entity to make sure that this class gets mapped to any database
@Entity
@Data
public class Employee implements Serializable {
@Id //Primary Key
@GeneratedValue(strategy= GenerationType.IDENTITY)//How to generate value
@Column(nullable = false,updatable = false)
    private Long id;

    @NotBlank(message = "Name can not be empty")
    private String name;

    @NotBlank(message = "Email must not be empty")
    @Email(message = "Email must e in the correct format")
    private String email;

    @NotBlank(message = "Job title must not be empty")
    private String jobTitle;

    @NotBlank(message = "Please enter your phone number ")
    @Size(min = 10, message = "Please enter a valid phone number")
    private String phone;

    private String imageUrl;

    //Connect to Team table
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "teamId")  // Name of the foreign key column
    private Team team;

    @Column(nullable = false,updatable = false)
    private String employeeCode;


}
