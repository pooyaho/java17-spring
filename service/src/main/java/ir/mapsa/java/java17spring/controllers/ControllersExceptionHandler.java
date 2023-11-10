package ir.mapsa.java.java17spring.controllers;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllersExceptionHandler.class);


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
        LOGGER.error("Error occurred in controller!",e);
        String locale = request.getHeader("locale");

        Properties properties = this.propertiesMap.get(locale);
        if (properties == null) {
            properties = this.propertiesMap.get("fa_IR");
        }
        String errorKey = e.getClass().getName();
        if (e instanceof MethodArgumentNotValidException) {
            for (FieldError fieldError : ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors()) {
                errorKey = fieldError.getDefaultMessage();
            }
            System.out.println(errorKey);
        }
        Object translate = properties.get(errorKey);
        if (translate == null) {
            translate = properties.get(Exception.class.getName());
        }
        if (e.getCause() != null && e.getCause() instanceof UnrecognizedPropertyException) {
            String propertyName = ((UnrecognizedPropertyException) e.getCause()).getPropertyName();
            translate = String.format(String.valueOf(translate), propertyName);
        }
        return ExceptionDto.builder()
                .errorCode(10)
                .message(String.valueOf(translate))
                .timestamp(new Date())
                .build();
    }
}