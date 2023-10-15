package ir.mapsa.java.java17spring.controllers;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@ControllerAdvice
public class ControllersExceptionHandler {
    private static final String[] LOCALES = {"fa_IR", "en_US"};
    private final Map<String, Properties> propertiesMap = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {
        for (String locale : LOCALES) {
            Properties properties = new Properties();
            try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("exceptions_" + locale + ".properties");
                 InputStreamReader in = new InputStreamReader(inputStream, "utf-8");) {
                properties.load(in);
                propertiesMap.put(locale, properties);
            }
        }
    }

//    @ExceptionHandler(Exception.class)
//    public ExceptionDto handle(Exception e) {
//
//    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionDto handle(Exception e, HttpServletRequest request) {
        String locale = request.getHeader("locale");

        Properties properties = this.propertiesMap.get(locale);
        if (properties == null) {
            properties = this.propertiesMap.get("fa_IR");
        }
        Object translate = properties.get(e.getClass().getName());
        if (translate == null) {
            translate = properties.get(Exception.class.getName());
        }
        return ExceptionDto.builder()
                .errorCode(10)
                .message(String.valueOf(translate))
                .timestamp(new Date())
                .build();
    }
}