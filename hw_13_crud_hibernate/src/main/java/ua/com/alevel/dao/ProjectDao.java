package ua.com.alevel.dao;

import ua.com.alevel.entity.Project;

public interface ProjectDao extends BaseDao<Project> {

    boolean isValidProjectData(Project project);
}
