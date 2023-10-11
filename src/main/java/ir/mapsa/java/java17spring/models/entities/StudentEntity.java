package ir.mapsa.java.java17spring.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "STUDENT", indexes = {@Index(name = "NAME_INDEX", columnList = "NAME")})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String family;
    @Column(nullable = false)
    private Integer age;
    @Column(name = "FATHER", length = 10)
    private String fatherName;
    @Column(length = 10, unique = true)
    private String nationalCode;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CourseEntity> passedCourses;

    @Version
    private Integer version;
//    public static void main(String[] args) {
//        System.out.println(UUID.randomUUID().toString());
//        System.out.println(UUID.randomUUID().toString());
//    }
}
