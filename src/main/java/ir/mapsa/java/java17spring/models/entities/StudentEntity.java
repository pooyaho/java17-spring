package ir.mapsa.java.java17spring.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

//@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "STUDENT", indexes = {@Index(name = "NAME_INDEX", columnList = "NAME")})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries(
        {
                @NamedQuery(name="StudentEntity.query1",query="select e from StudentEntity  e")
        }
)
@DiscriminatorValue("1")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10)
    private String studentId;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "st_co",joinColumns = @JoinColumn(),inverseJoinColumns = @JoinColumn())
    private List<CourseEntity> passedCourses;
    @Enumerated(EnumType.STRING)
    @Transient
    private StudyField field;
    @ElementCollection
    private Set<String> borrowedBooks;
    private String name;
    private String family;
    @Column(nullable = false)
    private Integer age;
    @Column(name = "FATHER", length = 10)
    private String fatherName;
    @Column(length = 10, unique = true)
    private String nationalCode;
    @Version
    private Integer version;

//    public static void main(String[] args) {
//        System.out.println(UUID.randomUUID().toString());
//        System.out.println(UUID.randomUUID().toString());
//    }

    @Lob
    private byte[] picture;

}
