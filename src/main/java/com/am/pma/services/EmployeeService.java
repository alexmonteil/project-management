package com.am.pma.services;

import com.am.pma.dao.IEmployeeRepository;
import com.am.pma.dto.IEmployeeProject;
import com.am.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    IEmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public List<IEmployeeProject> getEmployeeProjects() {
        return employeeRepository.employeeProjects();
    }

    public Employee findByEmployeeId(long employeeId) {
        return employeeRepository.findByEmployeeId(employeeId);
    }

    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
}
