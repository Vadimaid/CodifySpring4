package kg.codify.codifyspring4.service.impl;

import kg.codify.codifyspring4.configuration.OurCustomTestConfiguration;
import kg.codify.codifyspring4.dto.UserCreateDto;
import kg.codify.codifyspring4.dto.UserResponseDto;
import kg.codify.codifyspring4.entity.User;
import kg.codify.codifyspring4.repository.UserRepository;
import kg.codify.codifyspring4.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(properties = {
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.url=jdbc:h2:mem:public",
        "spring.datasource.username=sa",
        "spring.datasource.password=password",
        "spring.jpa.hibernate.ddl-auto=create",
        "spring.flyway.enabled=false",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect"
})
public class UserServiceImplITTest {

    @SpyBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Test
    public void testSave_OK() {
        UserCreateDto source = new UserCreateDto();
        source.setLogin("Test");
        source.setPassword("Test");
        source.setFullName("Test");
        User mockUser = new User(1L, "Test", "Test", "Test", Collections.emptySet());

        Mockito.when(passwordEncoder.encode("Test")).thenReturn("Test");
//        Mockito.when(userRepository.save(any())).thenReturn(mockUser);

        UserResponseDto user = userService.save(source);

        Assertions.assertNotNull(user);
        Assertions.assertEquals("Test", user.getLogin());
        Assertions.assertEquals("Test", user.getPassword());
        Assertions.assertEquals("Test", user.getFullName());
    }

    @Test
    public void testSave_UserAlreadyExist() {
        UserCreateDto source = new UserCreateDto();
        source.setLogin("Test");
        source.setPassword("Test");
        source.setFullName("Test");

        UserResponseDto user = userService.save(source);
        Assertions.assertNotNull(user);

        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> userService.save(source));
        Assertions.assertTrue(ex.getMessage().contains("User with login " + source.getLogin() + " already exists"));
    }
}
