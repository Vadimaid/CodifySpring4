package kg.codify.codifyspring4.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.codify.codifyspring4.dto.GreetingRequestDto;
import kg.codify.codifyspring4.dto.GreetingResponseDto;
import kg.codify.codifyspring4.service.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(value = "/first")
@Tag(name = "Первый контроллер",description = "Наш первый контроллер на который мы повесили Swagger")
public class FirstController {

    private final TranslatorService translatorService;

    @Autowired
    public FirstController(
            TranslatorService translatorService
    ) {
        this.translatorService = translatorService;
    }

    @GetMapping(value = "/test-get")
    @Operation(
            method = "Тест GET маппинга",
            summary = "Здесь мы смотрим как работает GET",
            description = "Здесь мы смотрим как работает GET"
    )
    @Parameters({
            @Parameter(
                    name = "name",
                    description = "Имя человека с которым поздороваться. Если не заполнено, вернется Hello world!",
                    example = "Vadim"
            )
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Все хорошо, вернется Hello + имя человека"),
            @ApiResponse(responseCode = "500", description = "В случае если переданное имя пустое")
    })
    public String hello(
            @RequestParam(name = "name", required = false) String name
    ) {
        if (Objects.isNull(name)) {
            return "Hello world";
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("Заданное имя не может быть пустым");
        }
        return "Hello " + name;
    }

    @PostMapping(value = "/test")
    public GreetingResponseDto sayHello(
            @RequestBody GreetingRequestDto source
    ) {
        String result = this.translatorService.translateGreeting(source.getName(), source.getLang());
        return new GreetingResponseDto(0, "SUCCESS", result);
    }

}
