package ir.mapsa.java.java17spring.controllers;

import ir.mapsa.java.java17spring.models.entities.StudentEntity;
import ir.mapsa.java.java17spring.respositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @PostMapping
    public void save(@RequestBody StudentEntity student) {
        repository.save(student);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/{id}")
    public StudentEntity findById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/")
    public List<StudentEntity> findByAll() {
        return repository.findAll();
    }

    @PostMapping("/search")
    public List<StudentEntity> findByExample(@RequestBody StudentEntity student) {
        return repository.findAll(Example.of(student,ExampleMatcher.matchingAll().withMatcher("family",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())));
    }
}
