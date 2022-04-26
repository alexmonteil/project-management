package com.am.pma.controllers;

import java.security.Principal;
import java.util.List;

import com.am.pma.entities.Project;
import com.am.pma.entities.Employee;
import com.am.pma.services.EmployeeService;
import com.am.pma.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;
    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public String displayProjects(Model model) {
        List<Project> projects = projectService.getAll();
        model.addAttribute("projectsList", projects);
        return "projects/list-projects";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model) {
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("project", new Project());
        model.addAttribute("employeesList", employees);
        return "projects/new-project";
    }

    @PostMapping("/create")
    public String createProject(@Valid Project project, Model model, Errors errors) {
        if (errors.hasErrors()) {
            return "projects/new-project";
        }

        projectService.save(project);
        // redirect after saving to DB to prevent duplicate submissions
        return "redirect:/projects";
    }

    @GetMapping("/update")
    public String displayUpdateProjectForm(Model model, @RequestParam("id") long projectId) {
        Project targetProject = projectService.findByProjectId(projectId);
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("project", targetProject);
        model.addAttribute("employeesList", employees);
        return "projects/update-project";
    }

    @PostMapping("/save")
    public String updateProject(@Valid Project project, Model model) {
        projectService.save(project);
        return "redirect:/projects";
    }

    @GetMapping("/delete")
    public String deleteProject(Model model, @RequestParam("id") long projectId) {
        Project targetProject = projectService.findByProjectId(projectId);
        projectService.deleteProject(targetProject);
        return "redirect:/projects";
    }

    @GetMapping("/timelines")
    public String displayProjectTimelines() {
        return "projects/projects-timelines";
    }

    @GetMapping("/my-projects")
    public String displayMyProjects(Principal principal, Model model) {

        Employee targetEmployee = employeeService.findByEmployeeUserName(principal.getName());
        List<Project> projectsList = projectService.getProjectsByEmployeeId(targetEmployee.getEmployeeId());
        model.addAttribute("projectsList", projectsList);
        return "projects/my-projects";
    }

    @GetMapping("/details")
    public String displayProjectDetails(Model model, @RequestParam("id") long projectId) {
        Project targetProject = projectService.findByProjectId(projectId);
        model.addAttribute("project", targetProject);
        return "projects/project-details";
    }

}
