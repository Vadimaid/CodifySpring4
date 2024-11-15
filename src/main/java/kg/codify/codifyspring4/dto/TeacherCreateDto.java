package kg.codify.codifyspring4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherCreateDto {

    private String name;
    private String surname;
    private SubjectCreateDto subject;
    private List<Long> classes;

}
