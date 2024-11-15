package kg.codify.codifyspring4.controller;

import kg.codify.codifyspring4.dto.AuthDto;
import kg.codify.codifyspring4.dto.UserCreateDto;
import kg.codify.codifyspring4.dto.UserResponseDto;
import kg.codify.codifyspring4.entity.User;
import kg.codify.codifyspring4.service.AuthenticationService;
import kg.codify.codifyspring4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping(value = "/register")
    public UserResponseDto register(@RequestBody UserCreateDto source) {
        return this.userService.save(source);
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody AuthDto authDto) {
        return authenticationService.authenticate(authDto.getLogin(), authDto.getPassword());
    }

    @GetMapping(value = "/asd")
    public String asd() {
        return "ASD";
    }
}
