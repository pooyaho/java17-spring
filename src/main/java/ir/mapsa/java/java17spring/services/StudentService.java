package ir.mapsa.java.java17spring.services;

import ir.mapsa.java.java17spring.models.entities.CourseEntity;
import ir.mapsa.java.java17spring.models.entities.QStudentEntity;
import ir.mapsa.java.java17spring.models.entities.StudentEntity;
import ir.mapsa.java.java17spring.respositories.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends AbstractService<StudentEntity, StudentRepository>{
    public static final QStudentEntity Q = QStudentEntity.studentEntity;
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @PostConstruct
//    public void init() {
//        StudentEntity student = entityManager.find(StudentEntity.class, 3);
//        System.out.println(student);
//    }
    public void doSomething() {
        Iterable<StudentEntity> entities = this.repository.findAll(
                Q.studentId.contains("123")
                        .and(Q.age.between(18,120)
                                .and(Q.borrowedBooks.contains("Maths"))
                                .or(Q.picture.isNotNull()))
        );

        repository.findAll(Q.passedCourses.contains(CourseEntity.builder().name("Maths").build()));
    }

}