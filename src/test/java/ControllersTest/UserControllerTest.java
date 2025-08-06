package ControllersTest;

import com.project.demo.controllers.UserController;
import com.project.demo.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    private UserService userService;
    private UserController userController;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    void testUserExists_ReturnsTrue() {
        Long userId = 100L;
        when(userService.existUser(userId)).thenReturn(true);

        boolean result = userController.userExists(userId);

        assertTrue(result);
        verify(userService, times(1)).existUser(userId);
    }

    @Test
    void testUserExists_ReturnsFalse() {
        Long userId = 101L;
        when(userService.existUser(userId)).thenReturn(false);

        boolean result = userController.userExists(userId);

        assertFalse(result);
        verify(userService, times(1)).existUser(userId);
    }

    @Test
    void testInsertUser() {
        Long userId = 200L;
        String userName = "Bogdan";

        userController.insertUser(userId, userName);

        verify(userService, times(1)).insertUser(userId, userName);
    }
}

