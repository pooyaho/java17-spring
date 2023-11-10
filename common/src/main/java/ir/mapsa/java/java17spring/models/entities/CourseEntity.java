package ir.mapsa.java.java17spring.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="COURSE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
