package kg.codify.codifyspring4.service;

import com.querydsl.core.types.Predicate;
import kg.codify.codifyspring4.dto.StudentAverageMarkDto;
import kg.codify.codifyspring4.entity.StudentSubjectEntity;

import java.util.List;

public interface StudentMarkService {

    void fillStudentMarks();

    List<StudentAverageMarkDto> getStudentAverageMarks();

    List<StudentSubjectEntity> getStudentsByClassAndMark(Long classId, Integer mark);

    List<StudentSubjectEntity> getAll(Predicate predicate);

}
