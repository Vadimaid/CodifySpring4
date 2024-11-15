package kg.codify.codifyspring4.controller;

import kg.codify.codifyspring4.dto.ClassDto;
import kg.codify.codifyspring4.entity.ClassEntity;
import kg.codify.codifyspring4.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {

    private final ClassService classService;

    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping(value = "/all")
    List<ClassEntity> getAllClasses() {
        System.out.println("Вызов метода контроллера");
        return this.classService.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(value = "/{id}")
    public ClassEntity getClassById(@PathVariable Long id) {
        System.out.println("Вызов метода контроллера");
        return this.classService.findById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = "/create")
    public ClassEntity createClass(@RequestBody ClassDto classDto) {
        System.out.println("Вызов метода контроллера");
        return this.classService.save(classDto.getName());
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping(value = "/by-name")
    public List<ClassEntity> getClassById(@RequestParam String name) {
        System.out.println("Вызов метода контроллера");
        return this.classService.findByName(name);
    }
}
