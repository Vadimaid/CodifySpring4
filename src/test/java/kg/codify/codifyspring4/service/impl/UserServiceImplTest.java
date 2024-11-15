package kg.codify.codifyspring4.service.impl;

import kg.codify.codifyspring4.dto.UserCreateDto;
import kg.codify.codifyspring4.dto.UserResponseDto;
import kg.codify.codifyspring4.entity.User;
import kg.codify.codifyspring4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testSave_OK() {
        UserCreateDto source = new UserCreateDto();
        source.setLogin("Test");
        source.setPassword("Test");
        source.setFullName("Test");
        User mockUser = new User(1L, "Test", "Test", "Test", Collections.emptySet());

        Mockito.when(passwordEncoder.encode("Test")).thenReturn("Test");
        Mockito.when(userRepository.save(any())).thenReturn(mockUser);

        UserResponseDto user = userService.save(source);

        Assertions.assertNotNull(user);
        Assertions.assertEquals(1L, user.getId());
        Assertions.assertEquals("Test", user.getLogin());
        Assertions.assertEquals("Test", user.getFullName());
    }

}