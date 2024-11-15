package kg.codify.codifyspring4.service.impl;

import kg.codify.codifyspring4.dto.UserCreateDto;
import kg.codify.codifyspring4.dto.UserResponseDto;
import kg.codify.codifyspring4.entity.User;
import kg.codify.codifyspring4.mapper.UserMapper;
import kg.codify.codifyspring4.repository.UserRepository;
import kg.codify.codifyspring4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return this.userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserResponseDto save(UserCreateDto source) {
        if (Objects.isNull(source)) {
            throw new IllegalArgumentException("UserCreateDto must not be null");
        }

        if (Objects.isNull(source.getLogin()) || source.getLogin().isBlank()) {
            throw new IllegalArgumentException("User login must not be null or empty");
        }

        if (Objects.isNull(source.getPassword()) || source.getPassword().isBlank()) {
            throw new IllegalArgumentException("User password must not be null or empty");
        }

        if (Objects.isNull(source.getFullName()) || source.getFullName().isBlank()) {
            throw new IllegalArgumentException("User full name must not be null or empty");
        }

        Optional<User> existingUser = this.userRepository.findByLogin(source.getLogin());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("User with login " + source.getLogin() + " already exists");
        }

        User user = new User();
        user.setLogin(source.getLogin());
        user.setPassword(this.passwordEncoder.encode(source.getPassword()));
        user.setFullName(source.getFullName());
        userRepository.save(user);
        return UserMapper.toResponseDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (Objects.isNull(username) || username.isEmpty()) {
            throw new UsernameNotFoundException("Username is incorrect");
        }
        return this.userRepository
                .findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
