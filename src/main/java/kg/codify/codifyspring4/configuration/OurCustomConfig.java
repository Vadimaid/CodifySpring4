package kg.codify.codifyspring4.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.Map;

@Profile("!test")
@Configuration
public class OurCustomConfig {

    @Bean
    public Map<String, String> templates() {
        Map<String, String> templates = new HashMap<>();
        templates.put("login_uuid", "84eba803-4c63-4768-b83f-d11cc83c37eb");
        return templates;
    }

}
