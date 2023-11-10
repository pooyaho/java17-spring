package ir.mapsa.java.java17spring.controllers;

import ir.mapsa.java.java17spring.models.Response;
import ir.mapsa.java.java17spring.models.Sample;
import ir.mapsa.java.java17spring.models.Sample2;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FirstController {



    @GetMapping("/hello1/{name}")
    public String sayHello1(@PathVariable String name) {
        return "Hello world " + name + "!";
    }

    @GetMapping("/hello2")
    public String sayHello(@RequestParam("name") String name) {
        return "Hello world " + name + "!";
    }

    @PostMapping("/hello")
    public String sayHello3(@RequestBody Sample sample) {
        return "Hello world " + sample.getName() + "!";
    }

    @PostMapping("/hello4/{family}")
    public String sayHello4(@RequestBody Sample sample, @PathVariable String family, @RequestParam("age") Integer age) {
        return "Hello " + sample.getName() + " " + family + " and you are " + age;
    }

    @PostMapping("/hello5")
    public ResponseEntity<Response> sayHello4(@RequestBody Sample2 sample, HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (!"Basic 1234455".equals(authorization)) {
            return ResponseEntity.status(401)
                    .header("result","notOk")
                    .body(Response.builder()
                            .payload("Unauthorized")
                            .build());
        }
        return ResponseEntity.ok(new Response());
    }

}

