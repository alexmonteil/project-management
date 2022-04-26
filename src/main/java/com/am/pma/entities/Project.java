package com.am.pma.entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
    @SequenceGenerator(name = "project_seq", sequenceName = "project_seq", allocationSize = 1, initialValue = 1)
    private long projectId;

    @NotBlank(message = "* Must provide a project name")
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank(message = "* Must provide a project stage")
    @Size(min = 2, max = 20)
    private String stage;

    @NotBlank(message = "* Must provide a project description")
    @Size(min = 2, max = 255)
    private String description;

    @NotNull
    @Valid
    private Date startDate;

    @NotNull
    @Valid
    private Date endDate;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name = "project_employee",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Message> messageList = new ArrayList<Message>();

    public Project() {}

    public Project(String name, String stage, String description, Date startDate, Date endDate) {
        this.name = name;
        this.stage = stage;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
