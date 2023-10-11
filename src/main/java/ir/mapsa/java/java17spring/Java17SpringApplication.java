package ir.mapsa.java.java17spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Java17SpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(Java17SpringApplication.class, args);
    }

}
