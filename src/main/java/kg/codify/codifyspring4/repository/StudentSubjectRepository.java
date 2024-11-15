package kg.codify.codifyspring4.repository;

import kg.codify.codifyspring4.entity.QStudentSubjectEntity;
import kg.codify.codifyspring4.entity.StudentSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubjectEntity, Long>,
        QuerydslPredicateExecutor<StudentSubjectEntity>, QuerydslBinderCustomizer<QStudentSubjectEntity> {

    @Override
    default void customize(QuerydslBindings bindings, QStudentSubjectEntity root) {
        bindings.bind(root.receivedFrom).first((path, value) -> root.receivedAt.after(value));
        bindings.bind(root.receivedTo).first((path, value) -> root.receivedAt.before(value));
    }

}
