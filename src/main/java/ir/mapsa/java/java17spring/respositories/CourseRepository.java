package ir.mapsa.java.java17spring.respositories;

import ir.mapsa.java.java17spring.models.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity,Long> {
}
