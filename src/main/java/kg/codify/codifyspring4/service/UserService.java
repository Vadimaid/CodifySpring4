package kg.codify.codifyspring4.service;

import kg.codify.codifyspring4.dto.UserCreateDto;
import kg.codify.codifyspring4.dto.UserResponseDto;
import kg.codify.codifyspring4.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findById(Long id);

    UserResponseDto save(UserCreateDto source);

}
