package kg.codify.codifyspring4.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;

import java.util.HashMap;
import java.util.Map;

@Profile("test")
@TestConfiguration
@TestPropertySource(value = "classpath:application-test.yml")
public class OurCustomTestConfiguration {

    @Bean
    public Map<String, String> templates() {
        Map<String, String> templates = new HashMap<>();
        templates.put("login_uuid", "test");
        return templates;
    }

}
