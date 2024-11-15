package kg.codify.codifyspring4.service.impl;

import kg.codify.codifyspring4.service.TranslatorService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value = "spring.profiles.active", havingValue = "test")
public class DevTranslatorServiceImpl implements TranslatorService {

    private final Environment environment;

    public DevTranslatorServiceImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String translateGreeting(String name, String lang) {
        if ("ru".equalsIgnoreCase(lang)) {
            return "Здравствуйте " + name;
        }
        if ("eng".equalsIgnoreCase(lang)) {
            return "Good morning " + name;
        }
        if ("kg".equalsIgnoreCase(lang)) {
            return "Саламатсызбы " + name;
        }
        return environment.getProperty("my.value");
    }
}
