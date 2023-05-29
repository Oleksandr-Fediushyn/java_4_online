package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.catalog.Catalog;

import java.util.Optional;

public interface CatalogService {
    Optional<Catalog> findById(Long id);
    Iterable<Catalog> findAllOrdered();
  }
