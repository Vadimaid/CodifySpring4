package kg.codify.codifyspring4.service.impl;

import kg.codify.codifyspring4.service.ServiceB;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ServiceBImpl implements ServiceB {

    private final Map<String, String> templates;

    @Override
    public String reworkString(String source) {
        if (Objects.isNull(source)) {
            throw new IllegalArgumentException("Null String");
        }
        if (source.trim().isEmpty()) {
            return "GOT_EMPTY";
        }
        String[] args = source.trim().split(" ");
        String template = templates.get("login_uuid");
        System.out.println("Template: " + template);

        return Arrays.stream(args)
                .map(Base64Util::encode)
                .map(x -> x + template)
                .collect(Collectors.joining("_"));
    }

}
