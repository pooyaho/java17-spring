package ir.mapsa.java.java17spring.controllers;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

class MyClass{
    public void printHi() {
        System.out.println("Hello world!");
    }
}

public class Main {
    public static void main(String[] args) {
        MyClass original = new MyClass();
        MyClass a = (MyClass) Enhancer.create(MyClass.class, new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("Before hello");
                method.invoke(original, args);
                System.out.println("After hello");
                return null;
            }
        });
        a.printHi();
    }
    public static void main2(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        A a = A.builder().name("A").build();
        B b=B.builder().name("B").build();
        a.setB(b);
        b.setA(a);
        String json = objectMapper.writeValueAsString(a);

        System.out.println(json);
        A a1 = objectMapper.readValue(json, A.class);
        System.out.println(a1);
    }
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class A{
    private String name;
    @JsonManagedReference
    private B b;
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class B{
    private String name;

    @JsonBackReference

    private A a;
}