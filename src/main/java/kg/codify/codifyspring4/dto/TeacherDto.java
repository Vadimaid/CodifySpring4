package kg.codify.codifyspring4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {

    private Long id;
    private String name;
    private String surname;
    private String subject;
    private List<String> classes;

    public TeacherDto(Long id, String name, String surname, String subject) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.subject = subject;
    }

}
