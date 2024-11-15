package kg.codify.codifyspring4.mapper;

import kg.codify.codifyspring4.dto.UserResponseDto;
import kg.codify.codifyspring4.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public UserResponseDto toResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setLogin(user.getLogin());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setFullName(user.getFullName());
        return userResponseDto;
    }
}
