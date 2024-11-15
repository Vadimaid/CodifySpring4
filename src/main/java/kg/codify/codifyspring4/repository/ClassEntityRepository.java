package kg.codify.codifyspring4.repository;

import jakarta.persistence.Tuple;
import kg.codify.codifyspring4.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassEntityRepository extends JpaRepository<ClassEntity, Long> {

    @Query(value = "select ce from ClassEntity ce")
    List<ClassEntity> findAllNative();

    List<ClassEntity> findByTitleContainsIgnoreCase(String title);
}
