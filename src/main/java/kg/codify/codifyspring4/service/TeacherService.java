package kg.codify.codifyspring4.service;

import kg.codify.codifyspring4.dto.TeacherCreateDto;
import kg.codify.codifyspring4.dto.TeacherDto;
import kg.codify.codifyspring4.entity.TeacherEntity;

import java.util.List;

public interface TeacherService {

    List<TeacherEntity> findAll();

    List<TeacherDto> findTeacherDtos();

    TeacherDto createTeacher(TeacherCreateDto source);

    TeacherDto findTeacher(Long id);

}
