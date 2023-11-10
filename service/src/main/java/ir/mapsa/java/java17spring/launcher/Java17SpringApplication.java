package ir.mapsa.java.java17spring.launcher;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = "ir.mapsa.java.java17spring")
@EntityScan(basePackages = "ir.mapsa.java.java17spring")
@EnableJpaRepositories(basePackages = "ir.mapsa.java.java17spring")
@EnableMongoRepositories(basePackages = "ir.mapsa.java.java17spring")
@EnableCaching
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableAsync
public class Java17SpringApplication {

//    @Autowired
//    private ObjectMapper objectMapper;

//    @PostConstruct
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        return objectMapper;
    }


    public static void main(String[] args) {
        SpringApplication.run(Java17SpringApplication.class, args);
    }
}
