package ir.mapsa.java.java17spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public abstract class AbstractMongoService<E,R extends MongoRepository<E,String>> {
    @Autowired
    protected R repository;

    public E save(E entity) {
        return repository.save(entity);
    }

    public void removeById(String id) {
        this.repository.deleteById(id);
    }

    public Optional<E> findById(String id) {
        return repository.findById(id);
    }
    public List<E> findAll() {
        return repository.findAll();
    }
    public List<E> findBySample(E entity) {
        return repository.findAll(Example.of(entity, ExampleMatcher.matchingAny()));
    }
}