package ua.com.alevel.persistence.repository.review;

import ua.com.alevel.persistence.entity.catalog.Catalog;
import ua.com.alevel.persistence.entity.review.Review;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.List;


public interface ReviewRepository extends BaseRepository<Review>{
    List<Review> findBySoftwareId(Long softwareId);
    List<Review> findByUserId(Long userId);
}
