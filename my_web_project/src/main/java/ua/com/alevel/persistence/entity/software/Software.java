package ua.com.alevel.persistence.entity.software;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.catalog.Catalog;
import ua.com.alevel.persistence.entity.review.Review;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "software")
public class Software extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "catalog_id")
    private Catalog catalog;

    @Column(name = "software_name", nullable = false)
    private String softwareName;
    @Column(name = "brief_description", nullable = false, columnDefinition = "TEXT")
    private String briefDescription;
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(name = "operating_system", nullable = false)
    private String operatingSystem;
    @Column(name = "license", nullable = false)
    private String license;
    @Column(name = "languages", nullable = false)
    private String languages;
    @Column(name = "site", nullable = false)
    private String site;
    @Column(name = "download",  nullable = false, columnDefinition = "TEXT")
    private String download;
    @Column(name = "image", nullable = false)
    private String image;
    @Column(name = "image_bg", nullable = false)
    private String imageBg;

    @OneToMany(mappedBy = "software", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    public Software() {
        super();
        reviews = new ArrayList<>();
    }

}
