package ir.mapsa.java.java17spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public abstract class AbstractService<E> {
    @Autowired
    private JpaRepository<E,Long> repository;

    public E save(E entity) {
        return repository.save(entity);
    }

    public void removeById(Long id) {
        this.repository.deleteById(id);
    }

    public Optional<E> findById(Long id) {
        return repository.findById(id);
    }
    public List<E> findAll() {
        return repository.findAll();
    }
    public List<E> findBySample(E entity) {
        return repository.findAll(Example.of(entity, ExampleMatcher.matchingAny()));
    }
}