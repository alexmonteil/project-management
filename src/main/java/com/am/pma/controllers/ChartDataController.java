package com.am.pma.controllers;

import java.util.List;
import com.am.pma.dto.IChartData;
import com.am.pma.dto.IProjectTimelineData;
import com.am.pma.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chartdata")
public class ChartDataController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/projects-status-counts")
    public List<IChartData> displayProjectsStatusCounts() {
        return projectService.getProjectsStatusCounts();
    }

    @GetMapping("/projects-timelines")
    public List<IProjectTimelineData> displayProjectsTimelinesData() { return projectService.getProjectsTimelinesData(); }

}
