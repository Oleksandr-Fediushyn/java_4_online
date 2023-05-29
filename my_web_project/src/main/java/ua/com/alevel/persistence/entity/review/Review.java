package ua.com.alevel.persistence.entity.review;


import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.software.Software;
import ua.com.alevel.persistence.entity.user.User;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "review")
public class Review extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "software_id")
    private Software software;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "rating", nullable = false)
    private int rating;
    @Column(name = "comment", nullable = false, columnDefinition = "TEXT")
    private String comment;


    public Review() {
        super();
    }

}
