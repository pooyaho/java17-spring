package ir.mapsa.java.java17spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Java17SpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(Java17SpringApplication.class, args);
    }

}
