package ir.mapsa.java.java17spring.respositories;

import ir.mapsa.java.java17spring.models.entities.CourseDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseMongoRepository extends MongoRepository<CourseDocument,String> {

}
