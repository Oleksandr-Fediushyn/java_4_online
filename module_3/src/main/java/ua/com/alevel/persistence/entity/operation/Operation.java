package ua.com.alevel.persistence.entity.operation;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.account.Account;
import ua.com.alevel.persistence.entity.expense_category.ExpenseCategory;
import ua.com.alevel.persistence.entity.income_category.IncomeCategory;
import ua.com.alevel.persistence.type.OperationType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "operations")
public class Operation extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "iban_sender")
    private String ibanSender;

    @Column(name = "iban_recipient")
    private String ibanRecipient;
    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "payment_reference")
    private String paymentReference;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private OperationType type;

    // Реляції багато-до-одного з таблицями income_categories та expense_categories
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "income_category_id")
    private IncomeCategory incomeCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expense_category_id")
    private ExpenseCategory expenseCategory;

    public Operation() {
        super();
        this.amount = new BigDecimal("00.00");
    }
}
