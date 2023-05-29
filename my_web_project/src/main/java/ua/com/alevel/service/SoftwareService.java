package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.catalog.Catalog;
import ua.com.alevel.persistence.entity.software.Software;

import java.util.List;
import java.util.Optional;

public interface SoftwareService  {
    List<Software>findAllByCatalogId(Long id);
    List<Software> findFullText(String string);
    Optional<Software> findById(Long id);
    Iterable<Software> findAllOrdered();
}
