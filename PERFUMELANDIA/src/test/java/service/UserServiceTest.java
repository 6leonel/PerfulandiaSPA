package model;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import model.User;
import repository.UserRepository;
import service.UserService;
import exception.UserNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    public void testFindUserById_WhenUserExists() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setName("Juan");
        user.setEmail("juan@example.com");
        user.setRole("USER");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        User result = userService.getUserById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Juan", result.getName());
        assertEquals("juan@example.com", result.getEmail());
        assertEquals("USER", result.getRole());
        verify(userRepository).findById(1L);
    }

    @Test
    public void testFindUserById_WhenUserDoesNotExist() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(1L);
        });
        verify(userRepository).findById(1L);
    }
}
