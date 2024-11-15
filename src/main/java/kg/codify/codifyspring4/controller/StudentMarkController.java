package kg.codify.codifyspring4.controller;

import com.querydsl.core.types.Predicate;
import kg.codify.codifyspring4.dto.StudentAverageMarkDto;
import kg.codify.codifyspring4.entity.StudentSubjectEntity;
import kg.codify.codifyspring4.service.StudentMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/student-mark")
public class StudentMarkController {

    private final StudentMarkService studentMarkService;

    @Autowired
    public StudentMarkController(StudentMarkService studentMarkService) {
        this.studentMarkService = studentMarkService;
    }

    @GetMapping(value = "/fill")
    public String fillStudentMarks() {
        studentMarkService.fillStudentMarks();
        return "OK";
    }

    @GetMapping(value = "/avg-marks")
    public List<StudentAverageMarkDto> avgStudentMarks() {
        return this.studentMarkService.getStudentAverageMarks();
    }

    @GetMapping(value = "/filter")
    public List<StudentSubjectEntity> filterStudentMarks(
            @RequestParam(name = "class", required = false) Long classId,
            @RequestParam(name = "mark", required = false) Integer mark
    ) {
        return this.studentMarkService.getStudentsByClassAndMark(classId, mark);
    }

    @GetMapping(value = "/all")
    public List<StudentSubjectEntity> allStudentMarks(
            @QuerydslPredicate(root = StudentSubjectEntity.class) Predicate predicate
    ) {
        return this.studentMarkService.getAll(predicate);
    }
}
