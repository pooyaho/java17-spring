package ir.mapsa.java.java17spring.respositories;

import ir.mapsa.java.java17spring.models.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
    void removeByNationalCode(String nationalCode);

    Optional<StudentEntity> findByNationalCode(String nationalCode);
    List<StudentEntity> findByFatherNameIsNotNull();
}
