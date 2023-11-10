package ir.mapsa.java.java17spring.converters;

import ir.mapsa.java.java17spring.models.entities.StudentDto;
import ir.mapsa.java.java17spring.models.entities.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentAdapter extends BaseAdapter<StudentDto,StudentEntity> {

}
