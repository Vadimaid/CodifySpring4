package kg.codify.codifyspring4.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Codify Spring 4")
                .version("1.0")
                .contact(new Contact().email("vadimaid@gmail.com"));

        return new OpenAPI().info(info);
    }
}
