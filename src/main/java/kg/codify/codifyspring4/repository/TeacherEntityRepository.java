package kg.codify.codifyspring4.repository;

import kg.codify.codifyspring4.dto.TeacherDto;
import kg.codify.codifyspring4.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherEntityRepository extends JpaRepository<TeacherEntity, Long> {

    @Query("""
        select new kg.codify.codifyspring4.dto.TeacherDto(te.id, te.name, te.surname, se.title)
        from TeacherEntity te
        join SubjectEntity se
    """)
    List<TeacherDto> findTeacherDtos();
}
