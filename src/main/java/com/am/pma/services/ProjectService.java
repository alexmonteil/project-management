package com.am.pma.services;

import com.am.pma.dao.IProjectRepository;
import com.am.pma.dto.IChartData;
import com.am.pma.dto.IEmployeeProject;
import com.am.pma.dto.IProjectTimelineData;
import com.am.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    IProjectRepository projectRepository;

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public Project findByProjectId(long projectId) {
        return projectRepository.findByProjectId(projectId);
    }

    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }

    public List<IChartData> getProjectsStatusCounts() { return projectRepository.getProjectsStatusCounts(); }

    public List<IProjectTimelineData> getProjectsTimelinesData() { return projectRepository.getProjectsTimelinesData(); }
}
