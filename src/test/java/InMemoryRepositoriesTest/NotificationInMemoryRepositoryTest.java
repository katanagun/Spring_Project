package InMemoryRepositoriesTest;

import com.project.demo.db.InMemory.NotificationInMemoryRepository;
import com.project.demo.db.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class NotificationInMemoryRepositoryTest {

    private NotificationInMemoryRepository repo;

    @BeforeEach
    void setUp() {
        repo = new NotificationInMemoryRepository();
    }

    @Test
    void saveNotification_AddsNewNotification() {
        Notification notif = new Notification(1L, 100L, 10L, "created");

        repo.saveNotification(notif);

        assertEquals(1, repo.notifications.size());
        assertTrue(repo.notifications.containsKey(1L));
        assertEquals(notif, repo.notifications.get(1L));
    }

    @Test
    void saveNotification_UpdatesExistingNotification() {
        Notification notif1 = new Notification(1L, 100L, 10L, "created");
        Notification notif2 = new Notification(1L, 100L, 10L, "updated");

        repo.saveNotification(notif1);
        repo.saveNotification(notif2);

        assertEquals(1, repo.notifications.size());
        assertEquals("updated", repo.notifications.get(1L).getNotificationValue());
    }

    @Test
    void findByUserId_ReturnsCorrectNotifications() {
        Notification n1 = new Notification(1L, 100L, 10L, "created");
        Notification n2 = new Notification(2L, 200L, 20L, "created");
        Notification n3 = new Notification(3L, 100L, 30L, "deleted");

        repo.saveNotification(n1);
        repo.saveNotification(n2);
        repo.saveNotification(n3);

        Collection<Notification> result = repo.findByUserId(100L);

        assertEquals(2, result.size());
        assertTrue(result.contains(n1));
        assertTrue(result.contains(n3));
        assertFalse(result.contains(n2));
    }

    @Test
    void findAll_ReturnsAllNotifications() {
        Notification n1 = new Notification(1L, 100L, 10L, "created");
        Notification n2 = new Notification(2L, 200L, 20L, "created");

        repo.saveNotification(n1);
        repo.saveNotification(n2);

        Collection<Notification> result = repo.findAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(n1));
        assertTrue(result.contains(n2));
    }
}

