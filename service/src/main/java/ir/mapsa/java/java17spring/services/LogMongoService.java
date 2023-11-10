package ir.mapsa.java.java17spring.services;

import ir.mapsa.java.java17spring.models.documents.RequestDocument;
import ir.mapsa.java.java17spring.models.documents.ResponseDocument;
import ir.mapsa.java.java17spring.respositories.RequestMongoRepository;
import ir.mapsa.java.java17spring.respositories.ResponseMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class LogMongoService {
    @Autowired
    private RequestMongoRepository requestMongoRepository;
    @Autowired
    private ResponseMongoRepository responseMongoRepository;

    @Async
    public CompletableFuture<RequestDocument> saveRequest(RequestDocument requestDocument) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (1 == 1) {
            throw new IllegalStateException();
        }
        return CompletableFuture.completedFuture(requestMongoRepository.save(requestDocument));
    }

    @Async
    public CompletableFuture<ResponseDocument> saveResponse(ResponseDocument requestDocument) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return CompletableFuture.completedFuture(responseMongoRepository.save(requestDocument));
    }
}
