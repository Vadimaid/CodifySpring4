package kg.codify.codifyspring4.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import kg.codify.codifyspring4.dto.StudentAverageMarkDto;
import kg.codify.codifyspring4.dto.StudentDto;
import kg.codify.codifyspring4.entity.QStudentSubjectEntity;
import kg.codify.codifyspring4.entity.StudentSubjectEntity;
import kg.codify.codifyspring4.repository.StudentSubjectRepository;
import kg.codify.codifyspring4.service.StudentMarkService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

@Service
public class StudentMarkServiceImpl implements StudentMarkService {

    private final StudentSubjectRepository studentSubjectRepository;
    private final EntityManager entityManager;

    @Autowired
    public StudentMarkServiceImpl(StudentSubjectRepository studentSubjectRepository, EntityManager entityManager) {
        this.studentSubjectRepository = studentSubjectRepository;
        this.entityManager = entityManager;
    }

    @Override
    public void fillStudentMarks() {
        throw new NotImplementedException();
    }

    @Override
    public List<StudentAverageMarkDto> getStudentAverageMarks() {
        return Collections.emptyList();
    }

    @Override
    public List<StudentSubjectEntity> getStudentsByClassAndMark(Long classId, Integer mark) {
        if (Objects.isNull(classId) && Objects.isNull(mark)) {
            return this.studentSubjectRepository.findAll();
        }

        BooleanBuilder builder = new BooleanBuilder();
        QStudentSubjectEntity root = QStudentSubjectEntity.studentSubjectEntity;

        if (Objects.nonNull(classId)) {
            builder.and(root.student.classEntity.id.eq(classId));
        }

        if (Objects.nonNull(mark)) {
            builder.and(root.grade.eq(mark));
        }

        if (Objects.isNull(builder.getValue())) {
            return this.studentSubjectRepository.findAll();
        }

        return StreamSupport.stream(
                this.studentSubjectRepository.findAll(builder.getValue()).spliterator(), false).toList();
    }

    @Override
    public List<StudentSubjectEntity> getAll(Predicate predicate) {
        if (Objects.isNull(predicate)) {
            return this.studentSubjectRepository.findAll();
        }

        return StreamSupport.stream(
                this.studentSubjectRepository.findAll(predicate).spliterator(), false).toList();
    }
}
