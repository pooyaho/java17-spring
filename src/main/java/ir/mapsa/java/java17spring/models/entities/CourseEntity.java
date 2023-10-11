package ir.mapsa.java.java17spring.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="COURSE")
@Data
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String field;
    @ManyToMany(mappedBy = "passedCourses",cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    private List<StudentEntity> students;
    @Version
    private Integer version;
}
