package ir.mapsa.java.java17spring.respositories;

import ir.mapsa.java.java17spring.models.entities.StudentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentMongoRepository extends MongoRepository<StudentDocument,String> {

}
