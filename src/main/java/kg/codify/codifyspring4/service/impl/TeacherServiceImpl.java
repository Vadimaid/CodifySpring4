package kg.codify.codifyspring4.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kg.codify.codifyspring4.dto.SubjectCreateDto;
import kg.codify.codifyspring4.dto.TeacherCreateDto;
import kg.codify.codifyspring4.dto.TeacherDto;
import kg.codify.codifyspring4.entity.ClassEntity;
import kg.codify.codifyspring4.entity.SubjectEntity;
import kg.codify.codifyspring4.entity.TeacherEntity;
import kg.codify.codifyspring4.mapper.TeacherMapper;
import kg.codify.codifyspring4.repository.ClassEntityRepository;
import kg.codify.codifyspring4.repository.SubjectRepository;
import kg.codify.codifyspring4.repository.TeacherEntityRepository;
import kg.codify.codifyspring4.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherEntityRepository teacherEntityRepository;
    private final SubjectRepository subjectRepository;
    private final ClassEntityRepository classEntityRepository;

    @Override
    public List<TeacherEntity> findAll() {
        return this.teacherEntityRepository.findAll();
    }

    @Override
    public List<TeacherDto> findTeacherDtos() {
        return this.teacherEntityRepository.findTeacherDtos();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public SubjectEntity findOrCreateSubject(SubjectCreateDto subject) {
        SubjectEntity subjectEntity = new SubjectEntity();
        if (Objects.nonNull(subject.getId())) {
            subjectEntity = this.subjectRepository
                    .findById(subject.getId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Предмет с ID " + subject.getId() + " не найден"
                    ));
        } else {
            if (Objects.isNull(subject.getTitle()) || subject.getTitle().isBlank()) {
                throw new IllegalArgumentException("Неправильно указано название нового предмета");
            }
            subjectEntity.setTitle(subject.getTitle());
        }
         return this.subjectRepository.save(subjectEntity);
    }

    @Transactional
    @Override
    public TeacherDto createTeacher(TeacherCreateDto source) {
        if (Objects.isNull(source)) {
            throw new IllegalArgumentException("Данные не могут отсутствовать");
        }
        if (Objects.isNull(source.getName()) || source.getName().isBlank()) {
            throw new IllegalArgumentException("Имя учителя не может быть пустым");
        }
        if (Objects.isNull(source.getSurname()) || source.getSurname().isBlank()) {
            throw new IllegalArgumentException("Фамилия учителя не может быть пустой");
        }
        if (Objects.isNull(source.getSubject())) {
            throw new IllegalArgumentException("Необходимо указать предмет учителя");
        }

        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setName(source.getName());
        teacherEntity.setSurname(source.getSurname());

        teacherEntity.setSubject(this.findOrCreateSubject(source.getSubject()));

        if (Objects.nonNull(source.getClasses()) && !source.getClasses().isEmpty()) {
            teacherEntity.setClasses(new ArrayList<>());
            for (Long classId : source.getClasses()) {
                ClassEntity classEntity = this.classEntityRepository
                        .findById(classId)
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Класс с ID " + classId + " не найден"
                        ));
                teacherEntity.getClasses().add(classEntity);
            }
        }

        return TeacherMapper.toDto(this.teacherEntityRepository.saveAndFlush(teacherEntity));
    }

    @Override
    public TeacherDto findTeacher(Long id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        return TeacherMapper.toDto(
                teacherEntityRepository.findById(id)
                        .orElseThrow(EntityNotFoundException::new)
        );
    }
}
