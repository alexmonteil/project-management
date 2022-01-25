package com.am.pma.controllers;

import java.io.IOException;
import java.util.List;

import com.am.pma.services.EmployeeService;
import com.am.pma.services.ImageService;
import com.am.pma.validators.OnUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import com.am.pma.entities.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@Validated
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    ImageService imageService;

    @GetMapping
    public String displayEmployees(Model model) {
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("employeesList", employees);
        return "employees/list-employees";
    }

    @GetMapping("/new")
    public String displayCreateEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/new-employee";
    }

    @GetMapping("/update")
    public String displayUpdateEmployeeForm(Model model, @RequestParam("id") long employeeId) {
        Employee targetEmployee = employeeService.findByEmployeeId(employeeId);
        model.addAttribute("employee", targetEmployee);
        return "employees/update-employee";
    }

    @PostMapping("/create")
    public String createEmployee(Model model, @Valid Employee employee, BindingResult bindingResult, @RequestParam("imageFile") MultipartFile multipartFile) throws IOException {

        if (bindingResult.hasErrors()) {
            return "employees/new-employee";
        }

        if (!multipartFile.isEmpty() && multipartFile.getOriginalFilename() != null) {
            employee.setImageData(multipartFile.getBytes());
            employee.setImageType(imageService.extractImageType(multipartFile));
        }

        employee.getUserAccount().setPassword(bCryptPasswordEncoder.encode(employee.getUserAccount().getPassword()));
        employee.getUserAccount().setEmployee(employee);
        employeeService.save(employee);
        // redirect after saving to DB to avoid duplicate submissions
        return "redirect:/employees";
    }

    @PostMapping("/save")
    public String updateEmployee(Model model, @Validated(OnUpdate.class) Employee employee, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "employees/update-employee";
        }

        Employee beforeUpdateEmployee = employeeService.findByEmployeeId(employee.getEmployeeId());
        beforeUpdateEmployee.setFirstName(employee.getFirstName());
        beforeUpdateEmployee.setLastName(employee.getLastName());
        beforeUpdateEmployee.setEmail(employee.getEmail());
        beforeUpdateEmployee.setPhoneNumber(employee.getPhoneNumber());
        beforeUpdateEmployee.setCareerDescription(employee.getCareerDescription());
        employeeService.save(beforeUpdateEmployee);
        return "redirect:/employees";
    }

    @GetMapping("/delete")
    public String deleteEmployee(Model model, @RequestParam("id") long employeeId) {
        Employee targetEmployee = employeeService.findByEmployeeId(employeeId);
        employeeService.deleteEmployee(targetEmployee);
        return "redirect:/employees";
    }

    @GetMapping("/profile")
    public String displayEmployeeProfile(Model model, @RequestParam("id") long employeeId) {
        Employee targetEmployee = employeeService.findByEmployeeId(employeeId);
        String imgUrl = imageService.convertByteArrayToFile(targetEmployee.getImageData(), targetEmployee.getImageType());
        model.addAttribute("imgUrl", imgUrl);
        model.addAttribute("employee", targetEmployee);
        return "employees/employee-profile";
    }
}
