package kg.codify.codifyspring4.mapper;

import kg.codify.codifyspring4.dto.TeacherDto;
import kg.codify.codifyspring4.entity.ClassEntity;
import kg.codify.codifyspring4.entity.TeacherEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TeacherMapper {

    public TeacherDto toDto(TeacherEntity source) {
        TeacherDto dto = new TeacherDto();
        dto.setId(source.getId());
        dto.setName(source.getName());
        dto.setSurname(source.getSurname());
        dto.setSubject(source.getSubject().getTitle());
        dto.setClasses(source.getClasses().stream().map(ClassEntity::getTitle).toList());
        return dto;
    }
}
