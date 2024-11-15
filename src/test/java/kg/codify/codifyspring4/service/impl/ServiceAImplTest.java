package kg.codify.codifyspring4.service.impl;

import kg.codify.codifyspring4.configuration.OurCustomTestConfiguration;
import kg.codify.codifyspring4.service.ServiceA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(OurCustomTestConfiguration.class)
class ServiceAImplTest {

    @Autowired
    private ServiceA serviceA;

    @Test
    public void testGetSomeToken_OK() {
        String username = "login";
        String password = "password";

        String result = serviceA.getSomeToken(username, password);
        System.out.println(result);
    }

}