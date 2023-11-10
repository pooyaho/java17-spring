package ir.mapsa.java.java17spring.models.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Document
public class StudentDocument {
    @org.springframework.data.annotation.Id
    private String id;
    @Indexed(unique = true)
    private String studentId;
    @DBRef()
    private List<CourseDocument> passedCourses;
    private Set<String> borrowedBooks;
    @Indexed()
    private String name;
    private String family;
    private Integer age;
    private String fatherName;
    @Indexed(unique = true)
    private String nationalCode;
}

