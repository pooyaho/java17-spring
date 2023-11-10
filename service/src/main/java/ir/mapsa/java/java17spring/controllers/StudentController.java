package ir.mapsa.java.java17spring.controllers;

import ir.mapsa.java.java17spring.models.entities.StudentDto;
import ir.mapsa.java.java17spring.models.entities.StudentEntity;
import ir.mapsa.java.java17spring.services.StudentService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional(readOnly = true)
@RestController
@RequestMapping("/student")
public class StudentController extends BaseAbstractController<StudentDto,StudentEntity, StudentService> {

}
