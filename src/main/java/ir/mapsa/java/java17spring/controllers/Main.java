package ir.mapsa.java.java17spring.controllers;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
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