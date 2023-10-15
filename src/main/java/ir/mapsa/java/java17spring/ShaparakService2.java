package ir.mapsa.java.java17spring;

import ir.mapsa.java.java17spring.respositories.StudentRepository;
import org.springframework.stereotype.Service;

public class ShaparakService2 implements ShaparakService {
    private StudentRepository repository;

    public void setRepository(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void sendRequest() {
        System.out.println("Hello from service 2");
    }
}
