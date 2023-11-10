package ir.mapsa.java.java17spring.respositories;

import ir.mapsa.java.java17spring.models.documents.ResponseDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResponseMongoRepository extends MongoRepository<ResponseDocument,String> {
}
