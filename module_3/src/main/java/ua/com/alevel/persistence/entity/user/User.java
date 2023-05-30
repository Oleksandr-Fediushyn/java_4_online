package ua.com.alevel.persistence.entity.user;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.account.Account;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    // Реляція один-до-багатьох з таблицею accounts
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts;

    public User() {
        super();
        accounts = new ArrayList<>();
    }

}
