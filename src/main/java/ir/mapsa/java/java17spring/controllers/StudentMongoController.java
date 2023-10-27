package ir.mapsa.java.java17spring.controllers;

import ir.mapsa.java.java17spring.models.entities.StudentDocument;
import ir.mapsa.java.java17spring.models.entities.StudentDocumentDto;
import ir.mapsa.java.java17spring.services.StudentMongoService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional(readOnly = true)
@RestController
@RequestMapping("/student/mongo")
public class StudentMongoController extends BaseAbstractMongoController<StudentDocumentDto, StudentDocument, StudentMongoService> {

}
