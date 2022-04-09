package com.am.pma.dao;

import java.util.List;
import com.am.pma.dto.IEmployeeProject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.am.pma.entities.Employee;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

public interface IEmployeeRepository extends CrudRepository<Employee, Long> {

    @Override
    @NonNull
    public List<Employee> findAll();

    @Query(nativeQuery = true, value = "SELECT e.first_name as firstName, e.last_name as lastName, e.email as email," +
            " COUNT(pe.employee_id) AS projectCount FROM employee e LEFT JOIN project_employee pe ON pe.employee_id = e.employee_id" +
            " GROUP BY e.first_name, e.last_name, e.email ORDER BY 3 DESC;")
    public List<IEmployeeProject> employeeProjects();

    @Query(nativeQuery = true, value = "SELECT * FROM employee e LEFT JOIN user_account ua ON e.user_id = ua.user_id WHERE ua.username = :username")
    public Employee findByEmployeeUserName(@Param("username") String username);

    public Employee findByEmployeeId(long employeeId);
    public Employee findByEmail(String email);
}
