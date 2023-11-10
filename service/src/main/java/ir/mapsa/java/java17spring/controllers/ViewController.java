package ir.mapsa.java.java17spring.controllers;

import ir.mapsa.java.java17spring.models.entities.StudentDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/view")
public class ViewController {

    @GetMapping
    public ModelAndView sayHelloWorld() {
        StudentDto studentDto = new StudentDto();
        Map<String, Object> model = new HashMap<>();
        model.put("student", studentDto);
        return new ModelAndView("hello",model);
    }
}
