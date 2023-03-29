package ua.com.alevel.service;

import ua.com.alevel.entity.Project;

import java.util.Collection;

public interface ProjectService {

    void create(Project entity);

    void update(Project entity, Long id);

    void delete(Project entity);

    Project findById(Long id);

    Collection<Project> findAll();

}
