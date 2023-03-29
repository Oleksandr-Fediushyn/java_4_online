package ua.com.alevel.service.impl;

import ua.com.alevel.dao.ProjectDao;
import ua.com.alevel.dao.impl.ProjectDaoImpl;
import ua.com.alevel.entity.Project;
import ua.com.alevel.service.ProjectService;

import java.util.Collection;
import java.util.Optional;

public class ProjectServiceImpl implements ProjectService {

    private final ProjectDao projectDao = new ProjectDaoImpl();

    @Override
    public void create(Project entity) {
        if (projectDao.isValidProjectData(entity)) {
            projectDao.create(entity);
        }
    }

    @Override
    public void update(Project entity, Long id) {
        Optional<Project> optionalProject = projectDao.findById(id);
        if (optionalProject.isPresent()) {
            entity.setId(id);
            if (projectDao.isValidProjectData(entity)) {
                projectDao.update(entity);
            }
        } else {
            System.out.println("Project entry not found\n");
        }
    }

    @Override
    public void delete(Project project) {
        projectDao.delete(project);
    }

    @Override
    public Project findById(Long id) {
        Optional<Project> optionalProject = projectDao.findById(id);
        if (optionalProject.isPresent()) {
            return projectDao.findById(id).get();
        }
        return optionalProject.orElseThrow(() -> new RuntimeException("Project entry not found\n"));
    }

    @Override
    public Collection<Project> findAll() {
        return projectDao.findAll();
    }
}
