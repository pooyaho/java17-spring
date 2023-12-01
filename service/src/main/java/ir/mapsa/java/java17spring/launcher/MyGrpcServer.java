package ir.mapsa.java.java17spring.launcher;


import ir.mapsa.services.MicroServiceGrpc;
import ir.mapsa.services.RequestPr;
import ir.mapsa.services.ResponsePr;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

// java -Xmx40g -Xms40g -XX:MaxPermSize=512m -jar MyApp.jar
@Service
public class MyGrpcServer extends MicroServiceGrpc.MicroServiceImplBase {
    @Override
    public void service1(RequestPr request, io.grpc.stub.StreamObserver<ResponsePr> responseObserver) {
        ResponsePr response = ResponsePr.newBuilder()
                .setPayload("Hello ")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String a = "123";
        byte[] salt = new byte[32];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(salt);
        byte[] encodedhash = digest.digest(
                a.getBytes(StandardCharsets.UTF_8));

        System.out.println(encodedhash.length);
        System.out.println(Base64.getEncoder().encodeToString(encodedhash));

    }


    @Override
    public void service2(RequestPr request, io.grpc.stub.StreamObserver<ResponsePr> responseObserver) {
    }

    @Override
    public void service3(RequestPr request, io.grpc.stub.StreamObserver<ResponsePr> responseObserver) {
        System.out.println("Hello world!");
    }
}
