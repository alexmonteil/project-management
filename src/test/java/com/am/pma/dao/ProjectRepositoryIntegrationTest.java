package com.am.pma.dao;

import com.am.pma.entities.Project;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ProjectRepositoryIntegrationTest {

    @Autowired
    IProjectRepository projectRepository;

    @Test
    public void ifNewProjectSaved_thenSuccess() {
        Project newProject = new Project("Test Project 1", "COMPLETE", "Generic description", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
        projectRepository.save(newProject);
        assertEquals(1, projectRepository.findAll().size());
    }
}
