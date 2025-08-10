package InMemoryRepositoriesTest;

import com.project.demo.db.InMemory.UserInMemoryRepository;
import com.project.demo.db.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInMemoryRepositoryTest {

    private UserInMemoryRepository repo;

    @BeforeEach
    void setUp() {
        repo = new UserInMemoryRepository();
    }

    @Test
    void insert_AddsNewUser() {
        Long userId = 1L;
        String userName = "Bogdan";

        repo.insert(userId, userName);

        assertEquals(1, repo.users.size());
        assertTrue(repo.users.containsKey(userId));

        User user = repo.users.get(userId);
        assertNotNull(user);
        assertEquals(userId, user.getUserId());
        assertEquals(userName, user.getUserName());
    }

    @Test
    void insert_OverwritesExistingUserWithSameId() {
        Long userId = 1L;

        repo.insert(userId, "Bogdan");
        repo.insert(userId, "Anton");

        assertEquals(1, repo.users.size());
        User user = repo.users.get(userId);
        assertEquals("Anton", user.getUserName());
    }

    @Test
    void existsByUserId_ReturnsTrue_WhenUserExists() {
        repo.insert(1L, "Bogdan");

        assertTrue(repo.existsByUserId(1L));
    }

    @Test
    void existsByUserId_ReturnsFalse_WhenUserDoesNotExist() {
        assertFalse(repo.existsByUserId(999L));
    }
}

