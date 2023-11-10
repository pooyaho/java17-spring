package ir.mapsa.java.java17spring.models.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@Document
public class CourseDocument {
    @org.springframework.data.annotation.Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String field;
}
