package ir.mapsa.java.java17spring.services;

import ir.mapsa.java.java17spring.models.entities.CourseDocument;
import ir.mapsa.java.java17spring.models.entities.StudentDocument;
import ir.mapsa.java.java17spring.respositories.CourseMongoRepository;
import ir.mapsa.java.java17spring.respositories.StudentMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentMongoService extends AbstractMongoService<StudentDocument, StudentMongoRepository> {
    @Autowired
    private CourseMongoRepository courseMongoRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public StudentDocument save(StudentDocument entity) {

        if (entity.getPassedCourses() != null) {
            Map<Boolean, List<CourseDocument>> listMap = entity.getPassedCourses().stream().collect(Collectors.groupingBy(i -> i.getId() == null, Collectors.toList()));
            List<CourseDocument> passedCourses=new ArrayList<>();
            if (listMap.get(true) != null) {
                passedCourses = courseMongoRepository.saveAll(listMap.get(true));
            }
            if (listMap.get(false) != null) {
                passedCourses.addAll(listMap.get(false));
            }
            entity.setPassedCourses(passedCourses);
        }
        return super.save(entity);
    }
}