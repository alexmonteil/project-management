package com.am.pma.entities;

import com.am.pma.validators.UniqueValue;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_account")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_account_seq")
    @SequenceGenerator(name = "user_account_seq", sequenceName = "user_account_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "user_id")
    private long userId;

    @NotBlank(message = "* Must provide a username")
    @Size(min = 4, max = 100)
    @Column(name = "username")
    private String userName;

    @NotBlank(message = "* Must provide a password")
    @Size(min = 4, max = 100)
    private String password;

    private String role = "ROLE_EMPLOYEE";
    private boolean enabled = true;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;


    public UserAccount() { }

    public UserAccount(String userName, String password, String role, boolean enabled) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
