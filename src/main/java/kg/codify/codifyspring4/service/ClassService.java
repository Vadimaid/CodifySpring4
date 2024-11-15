package kg.codify.codifyspring4.service;

import kg.codify.codifyspring4.entity.ClassEntity;

import java.util.List;

public interface ClassService {

    List<ClassEntity> findAll();
    ClassEntity findById(Long id);
    ClassEntity save(String name);
    List<ClassEntity> findByName(String name);

}
