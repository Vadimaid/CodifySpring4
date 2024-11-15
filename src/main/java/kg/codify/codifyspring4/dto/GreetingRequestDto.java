package kg.codify.codifyspring4.dto;

public class GreetingRequestDto {

    private String name;
    private String lang;

    public GreetingRequestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
