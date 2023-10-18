package ir.mapsa.java.java17spring.services;

import ir.mapsa.java.java17spring.models.entities.StudentEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends AbstractService<StudentEntity>{
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @PostConstruct
//    public void init() {
//        StudentEntity student = entityManager.find(StudentEntity.class, 3);
//        System.out.println(student);
//    }
}