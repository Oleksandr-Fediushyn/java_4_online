package ua.com.alevel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.catalog.Catalog;
import ua.com.alevel.persistence.repository.catalog.CatalogRepository;
import ua.com.alevel.service.CatalogService;

import java.util.Optional;

@Service
public class CatalogServiceImpl  implements CatalogService {
    private final CatalogRepository catalogRepository;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository) {

        this.catalogRepository = catalogRepository;
    }
    @Override
    public Optional<Catalog> findById(Long id) {
        Optional<Catalog> catalog = catalogRepository.findById(id);
        if (catalog.isEmpty()) {
            throw new NullPointerException("Software not found");
        }
        return catalog;
    }
    @Override
    public Iterable<Catalog> findAllOrdered() {
        return catalogRepository.findAllOrdered();
    }

}
