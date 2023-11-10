package ir.mapsa.java.java17spring.respositories;

import ir.mapsa.java.java17spring.models.entities.StudentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;


public interface StudentRepository extends JpaRepository<StudentEntity, Long>, QuerydslPredicateExecutor<StudentEntity> {
    void removeByNationalCode(String nationalCode);

    Optional<StudentEntity> findByNationalCode(String nationalCode);

    Stream<StudentEntity> findByFatherNameIsNotNull(Pageable pageable);

    @Query(value = "select * from student where fatherName=:fatherName", nativeQuery = true)
    CompletableFuture<List<StudentEntity>> findByFatherNameIsNotNullWithNativeQuery(@Param("fatherName") String fatherName);

    @Query(value = "select * from student", nativeQuery = true)
    List<StudentEntity> findAllWithNativeQuery();

    List<StudentEntity> query1();

    @Query(value = "select e from StudentEntity e join e.passedCourses p where p.name='Math 1'")
    List<StudentEntity> findByFatherNameIsNotNullWithQuery();

}
