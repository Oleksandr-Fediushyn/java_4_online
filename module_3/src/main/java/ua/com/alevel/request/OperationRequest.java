package ua.com.alevel.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.alevel.persistence.type.OperationType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationRequest {
    private Long account;
    private LocalDate date;
    private String ibanSender;
    private String ibanRecipient;
    private BigDecimal amount;
    private String paymentReference;
    private OperationType type;
    private Long incomeCategory;
    private Long expenseCategory;
}

