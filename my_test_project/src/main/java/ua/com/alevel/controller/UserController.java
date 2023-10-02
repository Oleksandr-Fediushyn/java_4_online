package ua.com.alevel.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.com.alevel.entity.User;
import ua.com.alevel.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Past;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Value("${user.minAge}")
    private int minAge; // Читаємо minimum age з properties file

    public int getMinAge() {
        return minAge;
    }

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@Valid @RequestBody User user, HttpServletResponse response) {

        if (userService.isUserAboveMinAge(user, getMinAge())) {
            User createdUser = userService.createUser(user);

            // Отримуємо URL нового ресурсу
            URI location = ServletUriComponentsBuilder
                    .fromPath("/users/{id}")
                    .buildAndExpand(createdUser.getId())
                    .toUri();

            // Додаємо заголовок Location до відповіді з URL нового ресурсу
            response.setHeader("Location", location.toString());

            return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Operation added unsuccessfully");
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateAllUserFields(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userService.updateAllUserFields(id, updatedUser);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchByBirthDateRange")
    public ResponseEntity<List<User>> getUsersByBirthDateRange(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Past(message = "Invalid 'from' date") LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Past(message = "Invalid 'to' date") LocalDate to) {
        if (from.isBefore(to)) {
            List<User> users = userService.getUsersByBirthDateRange(from, to);
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    // Обробник для IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Обробник для решти винятків
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }

}

