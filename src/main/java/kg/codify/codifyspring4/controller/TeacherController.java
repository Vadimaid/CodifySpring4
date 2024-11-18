package kg.codify.codifyspring4.controller;

import kg.codify.codifyspring4.dto.TeacherCreateDto;
import kg.codify.codifyspring4.dto.TeacherDto;
import kg.codify.codifyspring4.entity.TeacherEntity;
import kg.codify.codifyspring4.service.TeacherService;
import kg.codify.codifyspring4.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping(value = "/{id}")
    public TeacherDto getTeacher(@PathVariable Long id) {
        return teacherService.findTeacher(id);
    }

    @GetMapping(value = "/all")
    public List<TeacherEntity> getAllTeachers(){
        return this.teacherService.findAll();
    }

    @GetMapping(value = "/all-dto")
    public List<TeacherDto> getAllTeacherDtos(){
        return this.teacherService.findTeacherDtos();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = "/create")
    public TeacherDto hireNewTeacher(
            @RequestBody TeacherCreateDto source
    ) {
        return this.teacherService.createTeacher(source);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/update/{id}")
    public TeacherDto updateTeacher(
            @PathVariable(value = "id") Long teacherId,
            @RequestBody TeacherDto source
    ) {
        System.out.println("Якобы тут обновляется учитель");
        return source;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/remove/{id}")
    public void fireTeacher(
            @PathVariable(value = "id") Long teacherId
    ) {
        System.out.println("Тут якобы увольняется учитель " + teacherId);
    }
}
