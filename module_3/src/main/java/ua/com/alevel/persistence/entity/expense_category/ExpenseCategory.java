package ua.com.alevel.persistence.entity.expense_category;


import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.operation.Operation;
import ua.com.alevel.persistence.type.CategoryType;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "expense_categories")
public class ExpenseCategory extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CategoryType type;

    @OneToMany(mappedBy = "expenseCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Operation> operations;

}
