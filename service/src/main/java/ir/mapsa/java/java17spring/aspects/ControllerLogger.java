package ir.mapsa.java.java17spring.aspects;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.mapsa.java.java17spring.models.documents.RequestDocument;
import ir.mapsa.java.java17spring.models.documents.ResponseDocument;
import ir.mapsa.java.java17spring.services.LogMongoService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Aspect
@Component
public class ControllerLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerLogger.class);
    private ObjectMapper objectMapper = new ObjectMapper();
    private ExecutorService pool;

    @PostConstruct
    public void init() {
        pool = Executors.newFixedThreadPool(100);
    }

    @Autowired
    private LogMongoService logMongoService;

    @Pointcut("execution(* *.*(..))")
    public void allMethods() {

    }

    @Pointcut("execution(* ir.mapsa.java.java17spring.controllers.BaseAbstractMongoController+.*(..))")
    public void controllerMethods() {

    }

    @Around("controllerMethods() && allMethods()")
    public Object executeRestMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        String trackId = UUID.randomUUID().toString();
        Object[] args = proceedingJoinPoint.getArgs();

        if (args != null) {
            Object[] finalArgs = args;
            args = Arrays.stream(args).filter(i -> !(i instanceof ServletRequest))
                    .toArray(i -> new Object[finalArgs.length]);
        }
        RequestDocument req = RequestDocument.builder()
                .methodName(signature.getName())
                .input(args)
                .insertDate(new Date())
                .trackId(trackId)
                .className(proceedingJoinPoint.getThis().getClass().getName())
                .build();

        CompletableFuture<RequestDocument> requestDocument = this.logMongoService.saveRequest(req);

        requestDocument.whenComplete((result,exception)->{
            if (exception != null) {
                LOGGER.error("Error in async method!", exception);
            }else{
                try {
                    LOGGER.info("Success with result "+ objectMapper.writeValueAsString(result));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        LOGGER.info("Method " + signature.getName() + " called with track id :" + trackId + " and with params " + objectMapper.writeValueAsString(args));
        Object proceed;

        ResponseDocument res = ResponseDocument.builder()
                .trackId(trackId)
                .build();
        try {
            proceed = proceedingJoinPoint.proceed();
                res.setError(false);
            res.setResponseDate(new Date());
            res.setResponse(proceed);

        } catch (Exception e) {
            LOGGER.error("Exception occurred in method " + signature.getName() + " with track id :" + trackId + " and stack trace is " + ExceptionUtils.getStackTrace(e));
            res.setError(true);
            res.setResponse(ExceptionUtils.getStackTrace(e));
            res.setResponseDate(new Date());
            throw e;
        } finally {

            this.logMongoService.saveResponse(res);

        }
        LOGGER.info("Method " + signature.getName() + " executed with trakc id: " + trackId + " and return object " + objectMapper.writeValueAsString(proceed));
        return proceed;
    }
}

