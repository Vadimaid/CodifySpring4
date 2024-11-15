package kg.codify.codifyspring4.service.impl;

import kg.codify.codifyspring4.exception.AuthenticationException;
import kg.codify.codifyspring4.security.JwtTokenHandler;
import kg.codify.codifyspring4.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenHandler jwtTokenHandler;

    @Override
    public String authenticate(String login, String password) throws AuthenticationException {
        if (Objects.isNull(login) || Objects.isNull(password)) {
            throw new AuthenticationException("login and password are required");
        }

        if (login.trim().isBlank() || password.trim().isBlank()) {
            throw new AuthenticationException("login and password should not be empty");
        }

        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(login);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new AuthenticationException("Password is incorrect");
            }

            return jwtTokenHandler.generateToken(userDetails);
        } catch (UsernameNotFoundException ex) {
            throw new AuthenticationException(ex.getMessage());
        }
    }
}
