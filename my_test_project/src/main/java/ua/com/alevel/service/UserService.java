package ua.com.alevel.service;

import ua.com.alevel.entity.User;
import java.time.LocalDate;
import java.util.List;

public interface UserService {

    User createUser(User user);

    User updateUser(Long id, User updatedUser);

    boolean deleteUser(Long id);

    boolean isUserAboveMinAge(User user, int minAge);

    User updateAllUserFields(Long id, User updatedUser);

    List<User> getUsersByBirthDateRange(LocalDate fromDate, LocalDate toDate);
}
