package ir.mapsa.java.java17spring.launcher;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import ir.mapsa.services.MicroServiceGrpc;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Configuration
public class GrpcConfiguration {
    private Server server;
    @Bean
    public Server grpcServer(MyGrpcServer service) throws IOException {
        server = ServerBuilder.forPort(8089)
                .addService(service)
                .executor(Executors.newFixedThreadPool(400))
                .addService(ProtoReflectionService.newInstance())
                .build()
                .start();
        return server;
    }

    @PreDestroy
    public void stop() {
        server.shutdown();
    }

    @Bean
    public MicroServiceGrpc.MicroServiceBlockingStub clientStub() {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8089")
                .disableRetry()
                .executor(Executors.newFixedThreadPool(5))
                .keepAliveTime(10, TimeUnit.SECONDS)
                .keepAliveTimeout(5, TimeUnit.SECONDS)
                .idleTimeout(10, TimeUnit.MINUTES)
                .usePlaintext()
                .build();

        return MicroServiceGrpc.newBlockingStub(channel)
                ;
    }
}
