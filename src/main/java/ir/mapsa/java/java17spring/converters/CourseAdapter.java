package ir.mapsa.java.java17spring.converters;

import ir.mapsa.java.java17spring.models.entities.CourseDto;
import ir.mapsa.java.java17spring.models.entities.CourseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseAdapter extends BaseAdapter<CourseDto,CourseEntity>{

}
