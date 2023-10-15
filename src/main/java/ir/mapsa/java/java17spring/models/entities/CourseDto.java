package ir.mapsa.java.java17spring.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class CourseDto {
    private Long id;
    private String name;
    private String field;
    private Integer version;
}
