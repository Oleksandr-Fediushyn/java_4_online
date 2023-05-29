package ua.com.alevel.persistence.repository.catalog;


import org.springframework.data.jpa.repository.Query;
import ua.com.alevel.persistence.entity.catalog.Catalog;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.List;

public interface CatalogRepository extends BaseRepository<Catalog>{
    @Query(value = "SELECT * FROM software_catalogs.catalog ORDER BY catalog.catalog_name", nativeQuery = true)
    List<Catalog> findAllOrdered();

}
