package ua.com.alevel.persistence.repository.software;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.alevel.persistence.entity.software.Software;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.List;

public interface SoftwareRepository extends BaseRepository<Software> {

    @Query(value = "SELECT * FROM software ORDER BY software_name", nativeQuery = true)
    List<Software> findAllOrdered();

    List<Software> findAllByCatalogId(Long catalogID);
    @Query(value = "SELECT software.* FROM software LEFT JOIN catalog ON catalog.id = software.catalog_id WHERE MATCH (software.software_name, software.description, software.operating_system, software.license, software.languages) AGAINST (:string IN BOOLEAN MODE) ORDER BY software.software_name", nativeQuery = true)
    List<Software> findFullText(@Param("string") String string);

}
