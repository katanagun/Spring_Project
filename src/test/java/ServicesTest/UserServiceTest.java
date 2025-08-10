package ServicesTest;

import com.project.demo.db.repositories.UserRepository;
import com.project.demo.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserRepository userRepo;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepo = mock(UserRepository.class);
        userService = new UserService(userRepo);
    }

    @Test
    void existUser_ReturnsTrue_WhenUserExists() {
        Long userId = 1L;
        when(userRepo.existsByUserId(userId)).thenReturn(true);

        boolean result = userService.existUser(userId);

        assertTrue(result);
        verify(userRepo).existsByUserId(userId);
    }

    @Test
    void existUser_ReturnsFalse_WhenUserDoesNotExist() {
        Long userId = 2L;
        when(userRepo.existsByUserId(userId)).thenReturn(false);

        boolean result = userService.existUser(userId);

        assertFalse(result);
        verify(userRepo).existsByUserId(userId);
    }

    @Test
    void insertUser_CallsRepositoryInsert() {
        Long userId = 3L;
        String userName = "Bogdan";

        userService.insertUser(userId, userName);

        verify(userRepo).insert(userId, userName);
    }
}

