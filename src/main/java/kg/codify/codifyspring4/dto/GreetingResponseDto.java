package kg.codify.codifyspring4.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Ответ по приветствию")
public class GreetingResponseDto {

    @Schema(title = "Код", description = "Статус успешности запроса", example = "0")
    private Integer code;

    @Schema(title = "Сообщение", description = "SUCCESS в случае успеха, сообщение об ошибке в противном случае", example = "SUCCESS")
    private String message;

    @Schema(title = "Ответ", description = "Данные полученные от ораотки запроса", example = "Hello Vadim")
    private Object data;

    public GreetingResponseDto() {
    }

    public GreetingResponseDto(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
