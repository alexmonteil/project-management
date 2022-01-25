package com.am.pma.entities;


import com.am.pma.validators.OnUpdate;
import com.am.pma.validators.UniqueValue;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq", allocationSize = 1, initialValue = 1)
    private long employeeId;

    @NotBlank(message = "* Must provide a first name", groups = OnUpdate.class)
    @Size(min = 2, max = 50, groups = OnUpdate.class)
    private String firstName;

    @NotBlank(message = "* Must provide a last name", groups = OnUpdate.class)
    @Size(min = 2, max = 50, groups = OnUpdate.class)
    private String lastName;

    @NotBlank(message = "* Must provide an email address", groups = OnUpdate.class)
    @Email(message = "* Must be a valid email address", groups = OnUpdate.class)
    @UniqueValue(message = "* Email is already in use")
    private String email;

    @NotBlank(message = "* Must provide a phone number", groups = OnUpdate.class)
    @Size(min = 10, max = 13, groups = OnUpdate.class)
    private String phoneNumber;

    @NotBlank(message = "* Must provide a career description", groups = OnUpdate.class)
    @Size(min = 20, max = 255, groups = OnUpdate.class)
    private String careerDescription;

    private byte[] imageData;
    private String imageType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
    fetch = FetchType.LAZY)
    @JoinTable(name = "project_employee",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> projects;

    public Employee() {}

    public Employee(String firstName, String lastName, String email, String phoneNumber, String careerDescription, byte[] imageData, String imageType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.careerDescription = careerDescription;
        this.imageData = imageData;
        this.imageType = imageType;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getCareerDescription() {
        return careerDescription;
    }

    public void setCareerDescription(String careerDescription) {
        this.careerDescription = careerDescription;
    }
}
