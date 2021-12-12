package com.multimodule.service.api;

import java.util.List;

import com.multimodule.model.Student;

public interface StudentService {
    List<Student> getStudents();
    void addNewStudent(Student student);
    void deleteStudent(Long studentId);
    void updateStudent(Long studentId, String name, String email);
    
}
