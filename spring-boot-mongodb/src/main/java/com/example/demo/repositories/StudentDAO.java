package com.example.demo.repositories;

import java.util.List;

import com.example.demo.entities.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAO {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Student> findAll() {
        return mongoTemplate.findAll(Student.class);
    }

    public void saveAll(final List<Student> students) {
        mongoTemplate.insertAll(students);
    }

    public Student findById(final String studentId) {
        return mongoTemplate.findById(studentId, Student.class);
    }
}
