package ir.mapsa.java.java17spring.services;

import ir.mapsa.java.java17spring.models.Response;
import ir.mapsa.java.java17spring.models.Sample2;
import org.springframework.stereotype.Service;

@Service
public class SampleService {
    public Response doSomething(Sample2 sample) {
        String s = "Hello " + sample.getName() + " " + sample.getFamily() + " and you are " + sample.getAge();
        return new Response(s);
    }
}
