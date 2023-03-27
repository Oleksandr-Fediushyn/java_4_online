package ua.com.alevel.service.impl;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.dao.ProjectDao;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.entity.Project;
import ua.com.alevel.service.ProjectService;

import java.util.Collection;
import java.util.Optional;


@BeanClass
public class ProjectServiceImpl implements ProjectService {

    @InjectBean
    private ProjectDao projectDao;


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
    public void delete(Long id) {
        projectDao.delete(id);
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

    @Override
    public void attachEmployeeToProject(Long projectId, Long employeeId) {
        projectDao.attachEmployeeToProject(projectId, employeeId);
    }

    @Override
    public void deleteEmployeeFromProject(Long projectId, Long employeeId) {
        projectDao.deleteEmployeeFromProject(projectId, employeeId);
    }

}
