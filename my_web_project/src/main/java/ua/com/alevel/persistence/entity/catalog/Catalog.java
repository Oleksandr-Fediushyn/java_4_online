package ua.com.alevel.persistence.entity.catalog;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Table(name = "catalog")
public class Catalog extends BaseEntity {

    @Column(name = "catalog_name", nullable = false)
    private String catalogName;
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(name = "image", nullable = false)
    private String image;

    public Catalog() {
        super();
    }
}
