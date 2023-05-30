package ua.com.alevel.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.service.StatementExportService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/statements")
public class StatementController {

    private final StatementExportService statementExportService;

    public StatementController(StatementExportService statementExportService) {
        this.statementExportService = statementExportService;
    }

    @GetMapping("/{iban}")
    public void exportStatementToCsv(@PathVariable("iban") String iban,
                                     @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                     @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                     HttpServletResponse response) throws IOException {
        statementExportService.exportStatementToCsv(iban, startDate, endDate, response);
    }
}
