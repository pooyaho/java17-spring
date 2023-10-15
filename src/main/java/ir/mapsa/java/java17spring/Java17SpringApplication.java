package ir.mapsa.java.java17spring;

import ir.mapsa.java.java17spring.respositories.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Java17SpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(Java17SpringApplication.class, args);
    }

}
