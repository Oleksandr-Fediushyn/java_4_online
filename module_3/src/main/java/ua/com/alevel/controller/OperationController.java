package ua.com.alevel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.exception.InvalidOperationRequestException;
import ua.com.alevel.persistence.entity.operation.Operation;
import ua.com.alevel.request.OperationRequest;
import ua.com.alevel.service.OperationService;

import java.util.List;

@RestController
@RequestMapping("/api/operations")
public class OperationController {
    private final OperationService operationService;

    @Autowired
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewOperation(@RequestBody OperationRequest request) {
        try {
            operationService.addNewOperation(request);
            return ResponseEntity.ok("Operation added successfully");
        } catch (InvalidOperationRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
    @GetMapping
    public List<Operation> getAllOperations() {
        return operationService.getAllOperations();
    }
}

