package ir.mapsa.java.java17spring.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.mapsa.java.java17spring.models.validations.GeneralValidationGroup;
import ir.mapsa.java.java17spring.models.validations.NotNullGroup;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDocumentDto {
    private String id;
    @NotNull(groups = NotNullGroup.class,message = "student.name.isnull")
    @Size(groups = GeneralValidationGroup.class,max = 20,message = "student.name.invalid.size")
    @Pattern(groups = GeneralValidationGroup.class,regexp = "^[\\w|\\s]+$",message = "student.name.invalid.pattern")
    private String name;
    @NotNull(groups = NotNullGroup.class,message = "student.family.isnull")
    @Size(groups = GeneralValidationGroup.class,max = 20,message = "student.family.invalid.size")
    @Pattern(groups = GeneralValidationGroup.class,regexp = "^[\\w|\\s]+$",message = "student.family.invalid.pattern")
    private String family;
    @Min(groups = GeneralValidationGroup.class,value = 18,message = "student.age.invalid.min")
    @Max(groups = GeneralValidationGroup.class,value = 120,message = "student.age.invalid.max")
    private Integer age;
    @JsonProperty("father_name")
    @NotNull(groups = NotNullGroup.class,message = "student.fathername.isnull")
    @Size(groups = GeneralValidationGroup.class,max = 20,message = "student.fathername.invalid.size")
    @Pattern(groups = GeneralValidationGroup.class,regexp = "^[\\w|\\s]+$",message = "student.fathername.invalid.pattern")
    private String fatherName;
    @NotNull(groups = NotNullGroup.class,message = "student.nationalcode.isnull")
    @Size(groups = GeneralValidationGroup.class,min = 10,max = 10,message = "national-code.invalid.size")
    @Pattern(groups = GeneralValidationGroup.class,regexp = "^\\d+$",message = "national-code.invalid.pattern")
    private String nationalCode;
    private List<CourseDocumentDto> passedCourses;
}
