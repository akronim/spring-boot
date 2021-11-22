package com.example.demo.controllers;

import com.example.demo.entities.Student;
import com.example.demo.services.StudentService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {
    
    private final StudentService studentService;

    @PostMapping
    public void addStudents(@RequestBody final List<Student> students) {
        studentService.saveAll(students);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // here we will use MongoTemplate based repository
    @GetMapping("/{id}")
    public Student findStudent(@PathVariable final String id) {
        return studentService.findById(id);
    }

    // here we will use our custom repository
    @PatchMapping("/{studentId}")
    public void testMethod(@PathVariable final String studentId, @RequestBody final Student student) {
        studentService.testMethod(studentId, student);
    }
}
