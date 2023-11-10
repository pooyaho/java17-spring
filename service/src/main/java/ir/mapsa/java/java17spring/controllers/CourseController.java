package ir.mapsa.java.java17spring.controllers;

import ir.mapsa.java.java17spring.models.entities.CourseDto;
import ir.mapsa.java.java17spring.models.entities.CourseEntity;
import ir.mapsa.java.java17spring.services.CourseService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional(readOnly = true)
@RestController
@RequestMapping("/course")
public class CourseController  extends BaseAbstractController<CourseDto, CourseEntity, CourseService> {

}
