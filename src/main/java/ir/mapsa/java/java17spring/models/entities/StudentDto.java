package ir.mapsa.java.java17spring.models.entities;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private Long id;
    @NotNull
    private String name;
    private String family;
    private Integer age;
    private String fatherName;
    private String nationalCode;
    private List<CourseDto> passedCourses;
    private Integer version;
}
