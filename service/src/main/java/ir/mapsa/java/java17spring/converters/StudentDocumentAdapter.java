package ir.mapsa.java.java17spring.converters;

import ir.mapsa.java.java17spring.models.entities.StudentDocument;
import ir.mapsa.java.java17spring.models.entities.StudentDocumentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentDocumentAdapter extends BaseAdapter<StudentDocumentDto, StudentDocument> {

}
