package kg.codify.codifyspring4.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Tuple;
import kg.codify.codifyspring4.dto.ClassDto;
import kg.codify.codifyspring4.entity.ClassEntity;
import kg.codify.codifyspring4.repository.ClassEntityRepository;
import kg.codify.codifyspring4.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService {

    private final ClassEntityRepository classEntityRepository;

    @Autowired
    public ClassServiceImpl(ClassEntityRepository classEntityRepository) {
        this.classEntityRepository = classEntityRepository;
    }

    @Override
    public List<ClassEntity> findAll() {
        return this.classEntityRepository.findAllNative();
    }

    @Override
    public ClassEntity findById(Long id) {
        return this.classEntityRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Класс с ID " + id + " не найден"));
    }

    @Override
    public ClassEntity save(String name) {
        ClassEntity classEntity = new ClassEntity();
        classEntity.setTitle(name);
        return this.classEntityRepository.save(classEntity);
    }

    @Override
    public List<ClassEntity> findByName(String name) {
        return this.classEntityRepository.findByTitleContainsIgnoreCase(name);
    }

}
