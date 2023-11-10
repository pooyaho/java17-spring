package ir.mapsa.java.java17spring.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
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
                @NamedQuery(name = "StudentEntity.query1", query = "select e from StudentEntity  e")
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
    private List<CourseEntity> passedCourses;
    @Enumerated(EnumType.STRING)
    @Transient
    private StudyField field;
    @ElementCollection(fetch = FetchType.EAGER)
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

    @Lob
    private byte[] picture;

    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (
                FileInputStream fileInputStream = new FileInputStream("/Users/pooya/Downloads/MongoDB.ppt");
        ) {
            byte[] a = new byte[1024];
            int len;
            while ((len = fileInputStream.read(a)) != -1) {
                baos.write(a, 0, len);
            }
        }
        System.out.println(Base64.getEncoder().encodeToString(baos.toByteArray()));


    }
}

