package ua.com.alevel.dao;

import ua.com.alevel.entity.Project;

import java.util.Collection;
import java.util.Optional;

public interface ProjectDao {
    void create(Project entity);

    void update(Project entity);

    void delete(Long id);

    Optional<Project> findById(Long id);

    Collection<Project> findAll();

    void attachEmployeeToProject(Long projectId, Long employeeId);

    void deleteEmployeeFromProject(Long projectId, Long employeeId);

    boolean isValidProjectData(Project project);
}
