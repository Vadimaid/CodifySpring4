package kg.codify.codifyspring4.service.impl;

import kg.codify.codifyspring4.service.ServiceA;
import kg.codify.codifyspring4.service.ServiceB;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ServiceAImpl implements ServiceA {

    private final ServiceB serviceB;
    private final Environment environment;

    @Override
    public String getSomeToken(String username, String password) throws IllegalArgumentException {
        System.out.println(environment.getProperty("my.value"));

        if (Objects.isNull(username) || Objects.isNull(password)) {
            throw new IllegalArgumentException("Null is forbidden");
        }

        System.out.println("Got username and password: " + username + " " + password);
        System.out.println("Calculating hash...");
        int hash = Objects.hash(username.trim(), password.trim());
        System.out.println("Hash: " + hash);

        String reworkedString = serviceB.reworkString(username + " " + password);
        System.out.println("Reworked string: " + reworkedString);

        byte[] sourceOfUUID = (reworkedString + ":" + hash).getBytes();

        return UUID.nameUUIDFromBytes(sourceOfUUID).toString();
    }
}
