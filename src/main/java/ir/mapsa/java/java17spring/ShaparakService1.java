package ir.mapsa.java.java17spring;

import org.springframework.stereotype.Service;


public class ShaparakService1 implements ShaparakService {
    @Override
    public void sendRequest() {
        System.out.println("Hello from service 1");
    }
}
