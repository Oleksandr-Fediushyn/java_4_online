package ua.com.alevel.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.NoOperationsFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class StatementExportService {

    private final JdbcTemplate jdbcTemplate;

    public StatementExportService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void exportStatementToCsv(String iban, LocalDate startDate, LocalDate endDate, HttpServletResponse response) throws IOException {
        String sql = "SELECT date, type, amount, payment_reference FROM operations WHERE iban_sender = ? AND date >= ? AND date <= ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, iban, startDate, endDate);

        if (rows.isEmpty()) {
            throw new NoOperationsFoundException("No operations found for the specified period");
        }

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"statement.csv\"");

        PrintWriter writer = response.getWriter();

        // Write CSV header
        writer.println("Date,Type,Amount,Payment Reference");

        // Write CSV data
        for (Map<String, Object> row : rows) {
            String date = row.get("date").toString();
            String type = row.get("type").toString();
            String amount = row.get("amount").toString();
            String paymentReference = row.get("payment_reference").toString();

            writer.println(date + "," + type + "," + amount + "," + paymentReference);
        }

        writer.flush();
        writer.close();
    }
}

