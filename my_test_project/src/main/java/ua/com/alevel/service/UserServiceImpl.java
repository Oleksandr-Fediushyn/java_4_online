package ua.com.alevel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.com.alevel.entity.User;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;


@Service
public class UserServiceImpl implements UserService {

    private Long userIdCounter = 1L;

    private final int minAge;

    private final List<User> users = new ArrayList<>();

    @Autowired
    public UserServiceImpl(@Value("${user.minAge}") int minAge) {
        this.minAge = minAge;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public Optional<User> getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public User createUser(User user) {
        if (isUserAboveMinAge(user, minAge)) {
            user.setId(userIdCounter++);
            users.add(user);
            return user;
        }
        throw new IllegalArgumentException("User is not above the minimum age.");
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUser = getUserById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            // Оновлюємо дані користувача, які дозволені для оновлення
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setBirthDate(updatedUser.getBirthDate());
            if (updatedUser.getAddress() != null && !updatedUser.getAddress().isBlank()) {
                user.setAddress(updatedUser.getAddress());
            }
            if (updatedUser.getPhoneNumber() != null && !updatedUser.getPhoneNumber().isBlank()) {
                user.setPhoneNumber(updatedUser.getPhoneNumber());
            }
            return user;
        }
        throw new IllegalArgumentException("User not found with id: " + id);
    }

    @Override
    public boolean deleteUser(Long id) {
        boolean removed = users.removeIf(user -> user.getId().equals(id));
        if (!removed) {
            throw new IllegalArgumentException("User not found with id: " + id);
        }
        return removed;
    }

    @Override
    public List<User> getUsersByBirthDateRange(LocalDate fromDate, LocalDate toDate) {
        if (fromDate.isAfter(toDate)) {
            throw new IllegalArgumentException("From date must be before or equal to To date.");
        }
        List<User> filteredUsers = new ArrayList<>();
        for (User user : users) {
            LocalDate birthDate = user.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // Перевіряємо, чи дата народження користувача входить в діапазон
            if (birthDate.equals(fromDate) || birthDate.equals(toDate) ||
                    (birthDate.isAfter(fromDate) && birthDate.isBefore(toDate)) ||
                    birthDate.equals(toDate)) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }

    public boolean isUserAboveMinAge(User user, int minAge) {
        Calendar currentDate = Calendar.getInstance();

        // Перетворюємо дату народження користувача в Calendar
        Calendar birthDateCalendar = Calendar.getInstance();
        birthDateCalendar.setTime(user.getBirthDate());

        // Визначаємо різницю між поточним роком і роком народження
        int age = currentDate.get(Calendar.YEAR) - birthDateCalendar.get(Calendar.YEAR);

        // Перевіряємо, чи користувач старший за мінімальний вік
        return age >= minAge;
    }


    @Override
    public User updateAllUserFields(Long id, User updatedUser) {
        Optional<User> existingUser = getUserById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            // Оновлюємо всі поля користувача
            user.setEmail(updatedUser.getEmail());
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setBirthDate(updatedUser.getBirthDate());
            if (updatedUser.getAddress() != null && !updatedUser.getAddress().isBlank()) {
                user.setAddress(updatedUser.getAddress());
            }
            if (updatedUser.getPhoneNumber() != null && !updatedUser.getPhoneNumber().isBlank()) {
                user.setPhoneNumber(updatedUser.getPhoneNumber());
            }
            return user;
        }
        throw new IllegalArgumentException("User not found with id: " + id);
    }

}
