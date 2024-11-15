package kg.codify.codifyspring4.service.impl;

import kg.codify.codifyspring4.service.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnCloudPlatform;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value = "spring.profiles.active", havingValue = "production")
public class TranslatorServiceImpl implements TranslatorService {

    private final Environment environment;

    @Autowired
    public TranslatorServiceImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String translateGreeting(String name, String lang) {
        if ("ru".equalsIgnoreCase(lang)) {
            return "Здравствуйте " + name + "! Хорошего Вам дня!";
        }
        if ("eng".equalsIgnoreCase(lang)) {
            return "Good morning " + name + "! Have a nice day!";
        }
        if ("kg".equalsIgnoreCase(lang)) {
            return "Саламатсызбы " + name + "! Кутмандуу кун сизге!";
        }
        return environment.getProperty("my.value");
    }

}
