package ua.com.alevel.persistence.entity.account;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.operation.Operation;
import ua.com.alevel.persistence.entity.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "iban")
    private String iban;

    @Column(name = "balance")
    private BigDecimal balance;

    // Реляція один-до-багатьох з таблицею operations
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Operation> operations;

    public Account() {
        super();
        this.balance = new BigDecimal("00.00");
        operations = new ArrayList<>();
    }
}
