package ir.mapsa.java.java17spring;

import ir.mapsa.java.java17spring.respositories.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ShaparakService1 shaparakService1() {
        return new ShaparakService1();
    }

    @Bean
    public ShaparakService2 service2(StudentRepository studentRepository, @Value("shaparak.url") String shaparakUrl) {
        ShaparakService2 service2 = new ShaparakService2();
        service2.setRepository(studentRepository);
        return service2;
    }
}
