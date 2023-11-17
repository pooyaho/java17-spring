package ir.mapsa.java.java17spring.launcher;


import ir.mapsa.services.MicroServiceGrpc;
import ir.mapsa.services.RequestPr;
import ir.mapsa.services.ResponsePr;
import org.springframework.stereotype.Service;

@Service
public class MyGrpcServer extends  MicroServiceGrpc.MicroServiceImplBase{
    @Override
    public void service1(RequestPr request, io.grpc.stub.StreamObserver<ResponsePr> responseObserver) {
        ResponsePr response = ResponsePr.newBuilder()
                .setPayload("Hello ")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }





    @Override
    public void service2(RequestPr request, io.grpc.stub.StreamObserver<ResponsePr> responseObserver) {
    }

    @Override
    public void service3(RequestPr request, io.grpc.stub.StreamObserver<ResponsePr> responseObserver) {
        System.out.println("Hello world!");
    }
}
