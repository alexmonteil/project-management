package com.am.pma.controllers;

import java.util.List;

import com.am.pma.dto.IEmployeeProject;
import com.am.pma.entities.Project;
import com.am.pma.services.EmployeeService;
import com.am.pma.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    ProjectService projectService;
    @Autowired
    EmployeeService employeeService;


    @GetMapping("/")
    public String displayHome() {
        return "main/home";
    }

    @GetMapping("/dashboard")
    public String displayDashboard(Model model) {
        List<Project> projects = projectService.getAll();
        List<IEmployeeProject> employeesProjectCount = employeeService.getEmployeeProjects();
        model.addAttribute("projectsList", projects);
        model.addAttribute("employeesListProjectCount", employeesProjectCount);
        return "main/dashboard";
    }
}
