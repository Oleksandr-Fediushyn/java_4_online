package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class User extends BaseEntity{
    @NotBlank
    @Email(message = "Email address has invalid format: ${validatedValue}",
            regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    private String email;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    @Past(message = "Birth date must be in the past")
    private Date birthDate;

    private String address;

    @Pattern(regexp = "\\d{10}", message = "Invalid phone number format")
    private String phoneNumber;

    public User() {
        super();
    }
}
