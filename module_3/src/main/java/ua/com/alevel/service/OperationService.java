package ua.com.alevel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.InvalidOperationRequestException;
import ua.com.alevel.persistence.entity.account.Account;
import ua.com.alevel.persistence.entity.expense_category.ExpenseCategory;
import ua.com.alevel.persistence.entity.income_category.IncomeCategory;
import ua.com.alevel.persistence.entity.operation.Operation;
import ua.com.alevel.persistence.repository.account.AccountRepository;
import ua.com.alevel.persistence.repository.expense_category.ExpenseCategoryRepository;
import ua.com.alevel.persistence.repository.income_category.IncomeCategoryRepository;
import ua.com.alevel.persistence.repository.operation.OperationRepository;
import ua.com.alevel.request.OperationRequest;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OperationService {
    private final OperationRepository operationRepository;
    private final AccountRepository accountRepository;
    private final IncomeCategoryRepository incomeCategoryRepository;
    private final ExpenseCategoryRepository expenseCategoryRepository;

    @Autowired
    public OperationService(OperationRepository operationRepository,
                            AccountRepository accountRepository,
                            IncomeCategoryRepository incomeCategoryRepository,
                            ExpenseCategoryRepository expenseCategoryRepository) {
        this.operationRepository = operationRepository;
        this.accountRepository = accountRepository;
        this.incomeCategoryRepository = incomeCategoryRepository;
        this.expenseCategoryRepository = expenseCategoryRepository;
    }

    public void addNewOperation(OperationRequest request) {
        // Валідація операції
        validateOperationRequest(request);

        // Отримання рахунку відправника та отримувача
        Account senderAccount = accountRepository.findByIban(request.getIbanSender());
        Account recipientAccount = accountRepository.findByIban(request.getIbanRecipient());

        // Перевірка наявності рахунків
        if (senderAccount == null || recipientAccount == null) {
            throw new InvalidOperationRequestException("Invalid sender or recipient account");
        }

        // Оновлення балансів рахунків
        senderAccount.setBalance(senderAccount.getBalance().subtract(request.getAmount()));
        recipientAccount.setBalance(recipientAccount.getBalance().add(request.getAmount()));

        // Збереження операції
        Operation operation = new Operation();
        operation.setDate(request.getDate());
        operation.setIbanSender(request.getIbanSender());
        operation.setIbanRecipient(request.getIbanRecipient());
        operation.setAmount(request.getAmount());
        operation.setType(request.getType());
        operation.setPaymentReference(request.getPaymentReference());
        operation.setAccount((accountRepository.findById(request.getAccount()).get()));
        operation.setExpenseCategory(expenseCategoryRepository.findById(request.getExpenseCategory()).get());

        // Додатково встановлюємо категорію доходів або витрат, якщо вона вказана
        if (request.getIncomeCategory() != null) {
            IncomeCategory incomeCategory = incomeCategoryRepository.findById(request.getIncomeCategory())
                    .orElseThrow(() -> new InvalidOperationRequestException("Invalid income category"));
            operation.setIncomeCategory(incomeCategory);
        } else if (request.getExpenseCategory() != null) {
            ExpenseCategory expenseCategory = expenseCategoryRepository.findById(request.getExpenseCategory())
                    .orElseThrow(() -> new InvalidOperationRequestException("Invalid expense category"));
            operation.setExpenseCategory(expenseCategory);
        }

        operationRepository.save(operation);
        accountRepository.save(senderAccount);
        accountRepository.save(recipientAccount);
    }

    private void validateOperationRequest(OperationRequest request) {
        // Виконуємо перевірку на наявність помилок у запиті
        if (request.getAmount().compareTo(BigDecimal.ZERO) == 0) {
            throw new InvalidOperationRequestException("Operation amount must be greater than zero");
        }

        if ((request.getIncomeCategory() == null && request.getExpenseCategory() == null) ||
                (request.getIncomeCategory() != null && request.getExpenseCategory() != null)) {
            throw new InvalidOperationRequestException("Invalid operation category");
        }
    }

    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }
}

