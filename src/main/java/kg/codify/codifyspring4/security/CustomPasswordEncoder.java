package kg.codify.codifyspring4.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    private final BCryptPasswordEncoder innerPasswordEncoder = new BCryptPasswordEncoder(4);

    @Override
    public String encode(CharSequence rawPassword) {
        return innerPasswordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return innerPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
