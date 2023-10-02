package ua.com.alevel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ua.com.alevel.controller.UserController;
import ua.com.alevel.entity.User;
import ua.com.alevel.service.UserService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
@TestPropertySource(locations = "classpath:application.properties")
    public class MyTestApplicationTests {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private UserService userService;

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        LocalDate localDate = LocalDate.of(1990, 1, 1);
        user.setBirthDate(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        user.setAddress("123 Main St");
        user.setPhoneNumber("1234567890");

        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/createUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"firstName\":\"John\",\"lastName\":\"Doe\",\"birthDate\":\"1990-01-01\",\"address\":\"123 Main St\",\"phoneNumber\":\"1234567890\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

        @Test
        public void testCreateUserInvalidAge() throws Exception {
            User user = new User();
            user.setEmail("test@example.com");
            user.setFirstName("John");
            user.setLastName("Doe");
            LocalDate localDate = LocalDate.of(2006, 1, 1);
            user.setBirthDate(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            user.setAddress("123 Main St");
            user.setPhoneNumber("1234567890");

            when(userService.createUser(any(User.class))).thenThrow(new IllegalArgumentException("User is not above the minimum age."));

            mockMvc.perform(MockMvcRequestBuilders
                      .post("/users/createUser")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content("{\"email\":\"test@example.com\",\"firstName\":\"John\",\"lastName\":\"Doe\",\"birthDate\":\"2006-01-01\",\"address\":\"123 Main St\",\"phoneNumber\":\"1234567890\"}")
                    )
                    .andExpect(status().isBadRequest());
        }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Johnny");
        user.setLastName("Doe");
        LocalDate localDate = LocalDate.of(1990, 1, 1);
        user.setBirthDate(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        user.setAddress("125 Main St");
        user.setPhoneNumber("1234567890");
        when(userService.updateUser(anyLong(), any(User.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                    .put( "/users"+ "/" + user.getId())
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"email\":\"test@example.com\",\"firstName\":\"Johnny\",\"lastName\":\"Doe\",\"birthDate\":\"1990-01-01\",\"address\":\"125 Main St\",\"phoneNumber\":\"1234567890\"}")
                )
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateAllUserFields() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setEmail("test1@example.com");
        user.setFirstName("Johnny");
        user.setLastName("Doe");
        LocalDate localDate = LocalDate.of(1990, 1, 1);
        user.setBirthDate(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        user.setAddress("125 Main St");
        user.setPhoneNumber("1234567890");
        when(userService.updateAllUserFields(anyLong(), any(User.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                     .patch( "/users"+ "/" + user.getId())
                     .accept(MediaType.APPLICATION_JSON)
                     .contentType(MediaType.APPLICATION_JSON)
                     .content("{\"email\":\"test1@example.com\",\"firstName\":\"Johnny\",\"lastName\":\"Doe\",\"birthDate\":\"1990-01-01\",\"address\":\"125 Main St\",\"phoneNumber\":\"1234567890\"}")
                )
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteById() throws Exception {
        when(userService.deleteUser(anyLong())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                    .delete(( "/users"+ "/" + "/1"))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

        verify(userService, times(1)).deleteUser(1L);
    }

    }

