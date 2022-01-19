package com.am.pma;

import com.am.pma.dao.IEmployeeRepository;
import com.am.pma.dao.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProjectManagementApplication {

	@Autowired
	IEmployeeRepository employeeRepository;
	@Autowired
	IProjectRepository projectRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApplication.class, args);
	}

}
