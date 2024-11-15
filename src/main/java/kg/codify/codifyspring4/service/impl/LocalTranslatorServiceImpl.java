package kg.codify.codifyspring4.service.impl;

import kg.codify.codifyspring4.service.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value = "spring.profiles.active", havingValue = "local")
public class LocalTranslatorServiceImpl implements TranslatorService {

    private final Environment environment;

    @Autowired
    public LocalTranslatorServiceImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String translateGreeting(String name, String lang) {
        if ("ru".equalsIgnoreCase(lang)) {
            return "Привет " + name;
        }
        if ("eng".equalsIgnoreCase(lang)) {
            return "Hello " + name;
        }
        if ("kg".equalsIgnoreCase(lang)) {
            return "Салам " + name;
        }
        return environment.getProperty("my.value");
    }
}
