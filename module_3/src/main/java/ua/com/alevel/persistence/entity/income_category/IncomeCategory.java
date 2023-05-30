package ua.com.alevel.persistence.entity.income_category;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.type.CategoryType;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "income_categories")
public class IncomeCategory extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CategoryType type;

    public IncomeCategory() {
        super();
    }
}
