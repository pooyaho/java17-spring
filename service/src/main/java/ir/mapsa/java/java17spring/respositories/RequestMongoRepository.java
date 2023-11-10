package ir.mapsa.java.java17spring.respositories;

import ir.mapsa.java.java17spring.models.documents.RequestDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RequestMongoRepository extends MongoRepository<RequestDocument,String> {
}
