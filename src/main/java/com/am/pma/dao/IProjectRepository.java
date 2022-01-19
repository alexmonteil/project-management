package com.am.pma.dao;

import com.am.pma.dto.IProjectTimelineData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.am.pma.entities.Project;
import java.util.List;
import com.am.pma.dto.IChartData;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.lang.NonNullFields;

public interface IProjectRepository extends CrudRepository<Project, Long> {

    @Override
    @NonNull
    public List<Project> findAll();

    @Query(nativeQuery = true, value = "SELECT stage AS status, COUNT(*) AS count FROM project GROUP BY stage;")
    public List<IChartData> getProjectsStatusCounts();

    public Project findByProjectId(long projectId);

    @Query(nativeQuery = true, value = "SELECT name as projectName, start_date as startDate, end_date as endDate FROM project;")
    public List<IProjectTimelineData> getProjectsTimelinesData();
}
