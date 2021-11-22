package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Student;
import com.example.demo.repositories.StudentDAO;
import com.example.demo.repositories.StudentRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final StudentDAO studentDAO;

    public void saveAll(List<Student> students) {
        studentRepository.saveAll(students);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // here we are using MongoTemplate based repository
    public Student findById(String id) {
        return studentDAO.findById(id);
    }

    // here we are using our custom repository
    public void testMethod(String studentId, Student student) {
        studentRepository.testMethod(studentId, student);
    }
}
