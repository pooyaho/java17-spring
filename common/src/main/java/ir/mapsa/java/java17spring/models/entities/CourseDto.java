package ir.mapsa.java.java17spring.models.entities;

import lombok.Data;

@Data
public class CourseDto {
    private Long id;
    private String name;
    private String field;
    private Integer version;
}
